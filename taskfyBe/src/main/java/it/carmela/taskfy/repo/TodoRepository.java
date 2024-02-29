package it.carmela.taskfy.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import it.carmela.taskfy.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}