package com.hizon.model;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String password;
    private boolean admin;
    private List<BookDTO> books;
}
