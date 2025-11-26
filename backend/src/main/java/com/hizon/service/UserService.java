package com.hizon.service;

import com.hizon.model.UserDTO;

public interface UserService extends GenericService<UserDTO>{
    UserDTO findByName(String username);
}
