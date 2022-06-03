package com.example.test404.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = Person.TABLE_NAME)
public class Person {

    public static final String TABLE_NAME = "persons";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY
    private long id;

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Person(String first_name, String last_name, String email, String password, Role role, Status status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
