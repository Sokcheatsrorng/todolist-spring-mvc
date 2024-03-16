package co.istad.todolist.service;

import co.istad.todolist.model.Todo;
import co.istad.todolist.respository.TodoListDataSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TodoListDataSourceImpl implements TodoListService {
    TodoListDataSource dataSource = new TodoListDataSource();
    List<Todo> todoList = dataSource.getTodoList();
    private final AtomicInteger idCounter = new AtomicInteger(5);

    @Override
    public List<Todo> todoListDisplay() {
        return todoList;
    }
    @Override
    public Todo getTodoById(Integer id) {
        return todoList
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void addTodo(Todo todo) {
        int newId = idCounter.incrementAndGet();
        LocalDate getDate = LocalDate.now();
        todo.setId(newId);
        todo.setCreatedAt(getDate);
        todoList.add(todo);
    }
    @Override
    public void editTodo(Integer id, Todo updatedTodo) {
        LocalDate getDate = LocalDate.now();
        todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .ifPresent(todo -> {
                    updatedTodo.setId(id);
                    int index = todoList.indexOf(todo);
                    updatedTodo.setCreatedAt(getDate);
                    todoList.set(index, updatedTodo);
                });
    }
    @Override
    public void deleteTodo(Integer id) {
        todoList.removeIf(t -> t.getId().equals(id));
    }
    @Override
    public List<Todo> searchTodos(String task, boolean isDone) {
        if (task == null || task.isEmpty()) {
            return new ArrayList<>(todoList);
        } else {
            return todoList.stream()
                    .filter(t -> t.getTask().toLowerCase().contains(task.toLowerCase()) && t.getIsDone() == isDone)
                    .collect(Collectors.toList());
        }
    }
}
