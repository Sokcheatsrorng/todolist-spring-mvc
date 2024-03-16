package co.istad.todolist.service;

import co.istad.todolist.model.Todo;

import java.util.List;

public interface TodoListService {
    // display all todolist
    List<Todo> todoListDisplay();
    // get totoListById
    Todo getTodoById(Integer id);
    // add new todolist
    void addTodo(Todo todo);
    // edit existing todolist by id
    void editTodo(Integer id, Todo updateTodo);
    // delete existing todolist by id
    void deleteTodo(Integer id);
    // search existing todolist by id
    List<Todo> searchTodos(String task, boolean isDone);
}
