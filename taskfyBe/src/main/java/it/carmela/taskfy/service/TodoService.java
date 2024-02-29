package it.carmela.taskfy.service;
import org.springframework.stereotype.Service;

import it.carmela.taskfy.dto.TodoDto;
import it.carmela.taskfy.entity.Todo;
import it.carmela.taskfy.factory.TodoFactory;
import it.carmela.taskfy.repo.TodoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

  
    public List<TodoDto> getAll() {
        List<Todo> foundTodos = todoRepository.findAll();
        List<TodoDto> dtos = new ArrayList<>();

        for(Todo element : foundTodos)
            dtos.add(TodoFactory.fromTodoEntitytoDto(element));

        return dtos;
    }
    
    public Todo replaceTodobyId(Long id, Todo newTodo) {
        Todo toEditTodo = findById(id);
        toEditTodo.setName(newTodo.getName());
        toEditTodo.setCompleted(newTodo.isCompleted());
        toEditTodo.setDescription(newTodo.getDescription());

        return newTodo(toEditTodo);
    }

    public TodoDto getTodoDtoById(long id){
        Todo todoEntity = findById(id);
        TodoDto dtos = TodoFactory.fromTodoEntitytoDto(todoEntity);
        return dtos;
    }

    public void toggleTodoById(Long id){
        Todo toEditTodo = findById(id);
        toEditTodo.setCompleted(!toEditTodo.isCompleted());
        todoRepository.save(toEditTodo);
    }

    public Todo findById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Todo newTodo(Todo newTodo) {
        return todoRepository.save(newTodo);
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }
    public void deleteTodos() {
        todoRepository.deleteAll();
    }
}
