package com.bsu.pt.exam.exception;

public class NoSuchInviteCodeOrUser extends RuntimeException{
    public NoSuchInviteCodeOrUser() {
    }

    public NoSuchInviteCodeOrUser(String message) {
        super(message);
    }
}
