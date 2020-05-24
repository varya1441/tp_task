package com.bsu.pt.exam.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student")
//Пользователь - ФИО, логин, пароль, роль
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @Column(name = "last_name")
    private String lastName;

    private String login;

    private String password;

    private Role role;
    @ManyToOne
    private Group group;
    @ManyToOne
    private Result result;

    public Student(String login,String password){
        this.login=login;
        this.password=password;
    }


}
