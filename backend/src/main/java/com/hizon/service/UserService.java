package com.hizon.service;

import com.hizon.model.User;

public interface UserService extends GenericService<User>{
    User findByName(String username);
}
