package com.smitoi.roomatey.exceptions;

public class NotOwnerOfObject extends RuntimeException {

    protected Class aClass;

    @Override
    public String getMessage() {
        return "User is not owner of " + this.aClass.getSimpleName();
    }

    public NotOwnerOfObject(Class aClass) {
        super();
        this.aClass = aClass;
    }
}
