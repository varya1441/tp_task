package com.bsu.pt.exam.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Priority> priorities =new ArrayList<>();
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
                ", result=" + result.getId() +
                ", priority=" +
                ", checkedInvite=" + checkedInvite +
                '}';
    }

}
