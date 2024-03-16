package co.istad.todolist.respository;

import co.istad.todolist.model.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoListDataSource {
    public List<Todo> getTodoList() {
        List<Todo> todoList = new ArrayList<>();
        LocalDate getDate = LocalDate.now();
        todoList.add(new Todo(1,"Homework1","Need to Finish",true,getDate));
        todoList.add(new Todo(2,"Homework2","Need to Finish",false,getDate));
        todoList.add(new Todo(3,"Homework3","Need to Finish",true,getDate));
        todoList.add(new Todo(4,"Homework4","Need to Finish",true,getDate));
        todoList.add(new Todo(5,"Homework5","Need to Finish",false,getDate));
        return todoList;
    }

}
