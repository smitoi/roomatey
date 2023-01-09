package com.smitoi.roomatey.entity.definitions;

import com.smitoi.roomatey.entity.User;

public interface Ownable {

    boolean isOwnedBy(User user);
}
