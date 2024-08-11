package com.rean.springbootmaster.model;

import jakarta.persistence.*;
import lombok.*;


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
            orphanRemoval = true
            //cascade = CascadeType.ALL // when we save student we save studentIdCard, when we delete student we delete studentIdCard
    )
    private StudentIdCard studentIdCard;
}
