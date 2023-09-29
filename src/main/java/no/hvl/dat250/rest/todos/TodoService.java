package no.hvl.dat250.rest.todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final List<Todo> todos = new ArrayList<>();
    private Long idCounter = 1L;


    public TodoService() {
        // You can initialize some sample todos here if needed
    }

    public Todo createTodo(Todo todo) {
        todo.setId(idCounter++);
        todos.add(todo);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Optional<Todo> getTodoById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> optionalTodo = getTodoById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setSummary(updatedTodo.getSummary());
            todo.setDescription(updatedTodo.getDescription());
            return todo;
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        Optional<Todo> optionalTodo = getTodoById(id);
        if (optionalTodo.isPresent()) {
            todos.remove(optionalTodo.get());
            return true;
        }
        return false;
    }
}
