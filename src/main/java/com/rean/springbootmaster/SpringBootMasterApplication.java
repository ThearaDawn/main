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

		var fetchStudentFromDB = studentRepository.findFirstByPhoneNumber("1234567890");
		System.out.println("Student with phone number 1234567890");
		System.out.println(fetchStudentFromDB.orElse(null));

		var fetchStudentFromDB1 = studentRepository.findAllByAddress("Phnom Penh");
		System.out.println("Students with address Phnom Penh");
		fetchStudentFromDB1.forEach(System.out::println);

		var fetchStudentFromDB2 = studentRepository.findAllByAgeGreaterThan(20);
		System.out.println("Students with age greater than 20");
		fetchStudentFromDB2.forEach(System.out::println);
	}

}

