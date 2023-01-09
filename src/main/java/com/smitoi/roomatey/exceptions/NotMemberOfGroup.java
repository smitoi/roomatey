package com.smitoi.roomatey.exceptions;


public class NotMemberOfGroup extends RuntimeException {
    private static final String MESSAGE = "User is not member of group";

    public NotMemberOfGroup() {
        super(MESSAGE);
    }
}
