package com.rean.springbootmaster;

import com.rean.springbootmaster.model.Student;
import com.rean.springbootmaster.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterApplication.class, args);
	}

	@Autowired
	StudentRepository studentRepository;

	@PostConstruct
	public void init() {

		List<Student> students = List.of(
				Student.builder()
						.firstName("Rean")
						.lastName("Code")
						.age(25)
						.email("code123@gmail.com")
						.phoneNumber("089256789")
						.address("Phnom Penh")
						.build(),
				Student.builder()
						.firstName("Rean")
						.lastName("Java")
						.age(25)
						.email("rean123@gmail.com")
						.phoneNumber("0123456789")
						.address("Kompong Cham")
						.build(),
				Student.builder()
						.firstName("Rean")
						.lastName("Spring")
						.age(25)
						.email("reanspring@gmail.com")
						.phoneNumber("086789123")
						.address("Kompong Thom")
						.build()
		);

		// Save
		studentRepository.saveAll(students);

		// Sorting
		Sort sort = Sort.by(Sort.Direction.ASC, "address");
		List<Student> studentList = studentRepository.findAll(sort);
		System.out.println("Student List: ");
		studentList.forEach(System.out::println);

		// Pagination
		PageRequest pageRequest = PageRequest
				.of(0, 2, Sort.by("address").ascending());
		Page<Student> studentsPage = studentRepository.findAll(pageRequest);
		System.out.println("Student Page: " + pageRequest.getPageSize());

	}

}

