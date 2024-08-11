package com.rean.springbootmaster.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_id_card_number_unique",
                        columnNames = "card_number"
                )
        }
)
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    private Long id;

    @Column(
            name = "card_number",
            nullable = false
    )
    private String cardNumber;

    @ToString.Exclude // exclude the bi-directional relationship to avoid recursion
    @OneToOne(
            cascade = CascadeType.ALL
            //fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;


}
