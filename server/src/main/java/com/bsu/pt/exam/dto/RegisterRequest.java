package com.bsu.pt.exam.dto;


import com.bsu.pt.exam.entity.Role;

public class RegisterRequest {

    private String login;

    private String password;
    private String name;

    private String lastName;

    private String groupName;

    private String inviteCode;

    private boolean leader;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public boolean isLeader() {
        return leader;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        if (!leader) {
            return Role.STUDENT;
        } else {
            return Role.LEADER;
        }
    }
}
