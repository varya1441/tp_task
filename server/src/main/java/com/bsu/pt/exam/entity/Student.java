package com.bsu.pt.exam.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Group group;
    @ManyToOne
    @JsonIgnore
    private Result result;
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Priority priority;
    private Boolean checkedInvite = false;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", group=" + group.getGroupName() +
                ", result=" + result +
                ", priority=" + priority.getPriorities() +
                ", checkedInvite=" + checkedInvite +
                '}';
    }
}
