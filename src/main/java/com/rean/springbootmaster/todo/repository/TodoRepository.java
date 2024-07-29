package com.rean.springbootmaster.todo.repository;

import com.rean.springbootmaster.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * repository for communication with database
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findFirstById(long id);

    Optional<Todo> findFirstByIdAndAndCompleted(long id, boolean completed);

}