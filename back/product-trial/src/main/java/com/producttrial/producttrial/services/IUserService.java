package com.producttrial.producttrial.services;

import com.producttrial.producttrial.model.User;

public interface IUserService {
    User registerUser(User user);

    User getUserByEmail(String email);
}
