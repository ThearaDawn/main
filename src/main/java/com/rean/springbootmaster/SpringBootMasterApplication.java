package com.rean.springbootmaster;

import com.rean.springbootmaster.model.Student;
import com.rean.springbootmaster.model.StudentIdCard;
import com.rean.springbootmaster.repository.StudentIdCardRepository;
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
	@Autowired
	StudentIdCardRepository studentIdCardRepository;

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

		// we don't need to save because we use cascadeType.ALL on studentIdCard
		// studentRepository.saveAll(students);

		students.forEach(student -> {
			StudentIdCard studentIdCard = StudentIdCard.builder()
					.cardNumber("ID" + student.getPhoneNumber())
					.student(student)
					.build();
			studentIdCardRepository.save(studentIdCard);
		});

		System.out.println("Find all students");
		studentRepository.findAll().forEach(System.out::println);

		System.out.println("Find all student id card");
		studentIdCardRepository.findAll().forEach(System.out::println);


		// We need to add this cascade = CascadeType.ALL or orphanRemoval = true on studentIdCard in Student class,
		// if we want to delete student and studentIdCard
		studentRepository.deleteById(1L);
		System.out.println("Find all students after delete");
		studentRepository.findAll().forEach(System.out::println);
	}

}

