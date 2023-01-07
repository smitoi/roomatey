package com.smitoi.roomatey.exceptions;

public class NotOwnerOfGroup extends RuntimeException {

    private static final String MESSAGE = "User is not owner of group";
    public NotOwnerOfGroup(){
        super(MESSAGE);
    }
}
