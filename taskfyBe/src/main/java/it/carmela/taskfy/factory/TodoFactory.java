package it.carmela.taskfy.factory;

import it.carmela.taskfy.dto.TodoDto;
import it.carmela.taskfy.entity.Todo;

public class TodoFactory {

    public static TodoDto fromTodoEntitytoDto(Todo todoEntity) {

        TodoDto todoDto = new TodoDto();
        todoDto.setId(todoEntity.getId());
        todoDto.setName(todoEntity.getName());
        todoDto.setDescription(todoEntity.getDescription());
        todoDto.setDateExpired(todoEntity.getDateExpired());
        todoDto.calcolaRange();
        todoDto.setCompleted(todoEntity.isCompleted());
        todoDto.setDateCreate(todoEntity.getDateCreate());
        return todoDto;
    }

    public static Todo fromDtoToTodo(TodoDto todoDto) {
        Todo todoEntity = new Todo();
        todoEntity.setId(todoDto.getId());
        todoEntity.setName(todoDto.getName());
        todoEntity.setDescription(todoDto.getDescription());
        todoEntity.setDateExpired(todoDto.getDateExpired());
        todoEntity.setDateCreate(todoDto.getDateCreate());
        todoEntity.setCompleted(todoDto.isCompleted());
        return todoEntity;
    }
}
