package com.rean.springbootmaster.repository;

import com.rean.springbootmaster.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findFirstByPhoneNumber(String phoneNumber);

    List<Student> findAllByAddress(String address);

    List<Student> findAllByAgeGreaterThan(int age);
}
