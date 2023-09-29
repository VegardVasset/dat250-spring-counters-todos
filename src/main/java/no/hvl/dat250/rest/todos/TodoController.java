package no.hvl.dat250.rest.todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest-Endpoint for todos.
 */

@RestController
public class TodoController {

   public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        } else {
            String errorMessage = String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.getTodoById(id)
                .map(t -> ResponseEntity.ok(todoService.updateTodo(
                        id, todo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = todoService.deleteTodo(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            String errorMessage = String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}
