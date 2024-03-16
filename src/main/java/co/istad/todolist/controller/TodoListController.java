package co.istad.todolist.controller;

import co.istad.todolist.model.Todo;
import co.istad.todolist.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoListController {

    private final TodoListService service;

    public TodoListController(TodoListService service) {
        this.service = service;
    }
    @GetMapping
    public String index(Model model) {
        List<Todo> todos;
        todos = service.todoListDisplay();
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/{id}")
    public String showTodoDetails(@PathVariable int id, Model model) {
        Todo todo = service.getTodoById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @GetMapping("/new")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("/new")
    public String addTodo(@ModelAttribute Todo todo) {
        service.addTodo(todo);
        return "redirect:/todo";
    }

    @GetMapping("/edit/{id}")
    public String showEditTodoForm(@PathVariable int id, Model model) {
        Todo todo = service.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable int id, @ModelAttribute Todo updatedTodo) {
        service.editTodo(id, updatedTodo);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String setDeleteTodo(@PathVariable int id, Model model) {
        Todo todo = service.getTodoById(id);
        model.addAttribute("todo", todo);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) {
        service.deleteTodo(id);
        return "redirect:/todo";
    }

    @GetMapping("/search")
    public String searchTodoForm(Model model) {
        model.addAttribute("task", "");
        model.addAttribute("isDone", null);
        return "search";
    }

    @PostMapping("/search")
    public String searchTodoPost(@RequestParam String task,
                                 @RequestParam(required = false) Boolean isDone,
                                 Model model) {
        List<Todo> searchResults = service.searchTodos(task, isDone);
        model.addAttribute("todos", searchResults);
        return "index";
    }
}
