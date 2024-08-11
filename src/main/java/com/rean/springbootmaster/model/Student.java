package com.rean.springbootmaster.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "student_phone_number_unique",
                        columnNames = "phone_number"
                )
        }
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "phone_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String phoneNumber;
    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;


    // bi-directional relationship between student and studentIdCard when getting student we can get studentIdCard
    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
            //cascade = CascadeType.ALL // when we save student we save studentIdCard, when we delete student we delete studentIdCard
    )
    private StudentIdCard studentIdCard;

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        if (studentIdCard == null) {
            if (this.studentIdCard != null) {
                this.studentIdCard.setStudent(null);
            }
        } else {
            studentIdCard.setStudent(this);
        }
        this.studentIdCard = studentIdCard;
    }

    // one to many relationship between student and book
    @ToString.Exclude
    @OneToMany(
            mappedBy = "student",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY // lazy loading when we get student we don't get books
    )
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    // many to many relationship between student and course
    @ToString.Exclude
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}
    )
    @JoinTable(
            name = "enrolment",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    foreignKey = @ForeignKey(
                            name = "enrolment_student_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(
                            name = "enrolment_course_fk"
                    )
            )
    )
    private List<Course> courses = new ArrayList<>();

    public void enrolToCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void unEnrolToCourse(Course course) {
        if (this.courses.contains(course)) {
            this.courses.remove(course);
            course.getStudents().remove(this);
        }
    }
}
