package com.hizon.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hizon.entity.UserData;
import com.hizon.model.User;
import com.hizon.repository.UserRepository;
import com.hizon.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserData, User> implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repo, ModelMapper mapper){
        super(repo,mapper,UserData.class,User.class);
        this.userRepository = repo;
        this.mapper = mapper;
    } 

    @Override
    public User findByName (String username) {
        UserData entity = userRepository.findByName(username)
            .orElseThrow(() -> new RuntimeException("User not found with name: " + username));
        return mapper.map(entity, User.class);
    }

    @Override
    public User update(int id, User model){
        UserData entity = userRepository.findById(id).orElseThrow(() ->
            new RuntimeException(UserData.class.getSimpleName() + " not found with id: " + id));

        mapper.map(model, entity);

        if (model.getPassword() != null && !model.getPassword().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(model.getPassword()));
        }

        UserData saved = userRepository.save(entity);
        return mapper.map(saved, User.class);
    }

    @Override
    public User create(User model){
        UserData entity = mapper.map(model, UserData.class);

            if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                entity.setPassword(passwordEncoder.encode(model.getPassword()));
            }

        entity.setId(null);

        UserData saved = userRepository.save(entity);
        return mapper.map(saved, User.class);
    }
}
