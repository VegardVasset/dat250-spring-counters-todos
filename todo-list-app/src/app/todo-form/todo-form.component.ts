import { Component, ViewChild } from '@angular/core';
import { TodoService } from '../todo.service';
import { TodoListComponent } from '../todo-list/todo-list.component';
import { Todo } from '../todo.model'; // Import your Todo model if not already imported

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css'],
})
export class TodoFormComponent {
  // @ts-ignore
  todo: Todo = { id: undefined, description: '', summary: '' };

  @ViewChild(TodoListComponent) todoListComponent: TodoListComponent | undefined;

  constructor(private todoService: TodoService) {}

  addTodo(): void {
    // Call the addTodo method from TodoService with the todo data
    this.todoService.addTodo(this.todo).subscribe((newTodo) => {
      // Optionally, you can handle the response here (newTodo)
      console.log('New todo added:', newTodo);

      // Check if todoListComponent is defined before calling loadTodos
      if (this.todoListComponent) {
        this.todoListComponent.loadTodos();
      }

      // Clear the input fields by resetting this.todo to empty values
      // @ts-ignore
      this.todo = { id: undefined, description: '', summary: '' };
    });
  }
}
