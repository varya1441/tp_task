package com.bsu.pt.exam.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "student")
//Пользователь - ФИО, логин, пароль, роль
public class Student {
    public static final String DEFAULT_USER_LOGIN = "default";
    public static final String DEFAULT_USER_PASSWORD = "123456";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

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
    @OneToOne
    private Priority priority;

    public Student(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
