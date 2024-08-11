package com.rean.springbootmaster;

import com.rean.springbootmaster.model.Book;
import com.rean.springbootmaster.model.Course;
import com.rean.springbootmaster.model.Student;
import com.rean.springbootmaster.model.StudentIdCard;
import com.rean.springbootmaster.repository.StudentIdCardRepository;
import com.rean.springbootmaster.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

		Student student = Student.builder()
				.firstName("Rean")
				.lastName("Code")
				.age(25)
				.email("code123@gmail.com")
				.phoneNumber("089256789")
				.address("Phnom Penh")
				.books(new ArrayList<>())
				.courses(new ArrayList<>())
				.build();

		student.addBook(Book.builder()
				.title("Java")
				.description("Java is programming language")
				.author("Rean")
				.price(20.0)
				.quantity(10)
				.category("Programming")
				.createdAt(LocalDateTime.now())
				.student(student)
				.build());

		student.addBook(Book.builder()
				.title("Spring")
				.description("Spring is a framework")
				.author("Rean")
				.price(30.0)
				.quantity(5)
				.category("Programming")
				.createdAt(LocalDateTime.now())
				.student(student)
				.build());

		student.setStudentIdCard(StudentIdCard.builder()
				.cardNumber("ID" + student.getPhoneNumber())
				.student(student)
				.build());

		student.enrolToCourse(Course.builder()
				.name("Java")
				.department("Programming")
				.students(new ArrayList<>())
				.build());
		student.enrolToCourse(Course.builder()
				.name("Spring")
				.department("Programming")
				.students(new ArrayList<>())
				.build());
		studentRepository.save(student);

		var studentFetchFromDB = studentRepository.findById(1L);
		studentFetchFromDB.ifPresent(student1 -> {
			System.out.println(student1);
			// lazy loading will not fetch book when we get student
			//System.out.println(student1.getBooks());
		});
	}

}

