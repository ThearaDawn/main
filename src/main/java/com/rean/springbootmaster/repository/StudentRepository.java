package com.rean.springbootmaster.repository;

import com.rean.springbootmaster.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(
            value = "SELECT * FROM student WHERE phone_number = :phoneNumber",
            nativeQuery = true)
    Optional<Student> findFirstByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(
            value = "SELECT * FROM student WHERE address = ?1",
            nativeQuery = true)
    List<Student> findAllByAddress(String address);

    @Query("SELECT s FROM Student s WHERE s.age > ?1")
    List<Student> findAllByAgeGreaterThan(int age);

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM student WHERE id = :id",
            nativeQuery = true)
    int deleteStudentById(@Param("id") long id);
}
