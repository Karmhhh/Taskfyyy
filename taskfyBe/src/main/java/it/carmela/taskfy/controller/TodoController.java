package it.carmela.taskfy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.carmela.taskfy.service.TodoService;
import it.carmela.taskfy.dto.TodoDto;
import it.carmela.taskfy.entity.Todo;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> fetchedData = todoService.getAll();

        if (fetchedData.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(fetchedData);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findById/{id}")
    Todo findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/postTodo")
    Todo newTodo(@RequestBody Todo newTodo) {
        return todoService.newTodo(newTodo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/todo/{id}")
    Todo replaceTodo(@RequestBody Todo newTodo, @PathVariable Long id) {
        return todoService.replaceTodobyId(id, newTodo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/toggleTodo/{id}")
    void toggleTodoByIdMap(@PathVariable Long id) {
        todoService.toggleTodoById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{id}")
    void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete")
    void deleteTodos() {
        todoService.deleteTodos();
    }

}
