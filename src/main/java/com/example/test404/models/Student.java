package com.example.test404.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = Student.TABLE_NAME)

public class Student {

    public static final String TABLE_NAME = "students";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.IDENTITY
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String study_group;

    public Student(String first_name, String last_name, String email, String password, String study_group) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.study_group = study_group;
    }

}
