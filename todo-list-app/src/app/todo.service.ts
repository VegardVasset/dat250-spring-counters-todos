import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from './todo.model'; // Import your Todo model if not already imported

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private apiUrl = 'http://localhost:8080/todos'; // Update the URL to match your API endpoint

  constructor(private http: HttpClient) {}

  getAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}`);
  }

  addTodo(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(`${this.apiUrl}`, todo);
  }

  updateTodo(todo: Todo): Observable<Todo> {
    const url = `${this.apiUrl}/${todo.id}`;
    return this.http.put<Todo>(url, todo);
  }

  deleteTodo(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
