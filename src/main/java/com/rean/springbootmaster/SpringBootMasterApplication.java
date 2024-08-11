package com.rean.springbootmaster;

import com.rean.springbootmaster.model.Student;
import com.rean.springbootmaster.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterApplication.class, args);
	}

	@Autowired
	StudentRepository studentRepository;

	@PostConstruct
	public void init() {
		Student student = Student.builder()
				.firstName("Rean")
				.lastName("Code")
				.age(25)
				.email("reancode@gmail.com")
				.phoneNumber("1234567890")
				.address("Phnom Penh")
				.build();

		studentRepository.save(student);
		System.out.println("Log after save student: " + student);

		var students = studentRepository.findAll();
		System.out.println("Log after fetch all students: " + students);

		studentRepository.deleteById(1L);
		System.out.println("Log after delete student with id 1");

		students = studentRepository.findAll();
		System.out.println("Log after fetch all students: " + students);
	}

}

