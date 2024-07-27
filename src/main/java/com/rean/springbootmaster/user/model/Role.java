package com.rean.springbootmaster.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}