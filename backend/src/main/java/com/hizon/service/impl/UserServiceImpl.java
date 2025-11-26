package com.hizon.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hizon.entity.UserData;
import com.hizon.model.UserDTO;
import com.hizon.repository.UserRepository;
import com.hizon.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserData, UserDTO> implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repo, ModelMapper mapper){
        super(repo,mapper,UserData.class,UserDTO.class);
        this.userRepository = repo;
        this.mapper = mapper;
    } 

    @Override
    public UserDTO findByName (String username) {
        UserData entity = userRepository.findByName(username)
            .orElseThrow(() -> new RuntimeException("User not found with name: " + username));
        return mapper.map(entity, UserDTO.class);
    }

    @Override
    public UserDTO update(int id, UserDTO model){
        UserData entity = userRepository.findById(id).orElseThrow(() ->
            new RuntimeException(UserData.class.getSimpleName() + " not found with id: " + id));

        mapper.map(model, entity);

        if (model.getPassword() != null && !model.getPassword().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(model.getPassword()));
        }

        UserData saved = userRepository.save(entity);
        return mapper.map(saved, UserDTO.class);
    }

    @Override
    public UserDTO create(UserDTO model){
        UserData entity = mapper.map(model, UserData.class);

            if (model.getPassword() != null && !model.getPassword().isEmpty()) {
                entity.setPassword(passwordEncoder.encode(model.getPassword()));
            }

        entity.setId(null);

        UserData saved = userRepository.save(entity);
        return mapper.map(saved, UserDTO.class);
    }
}
