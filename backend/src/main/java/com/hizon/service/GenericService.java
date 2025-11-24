package com.hizon.service;
import java.util.List;

public interface GenericService<Model>{
    List<Model> readAll();
    Model create(Model model);
    Model read(Integer id);
    Model update(int id,Model model);
    void delete(Integer id);
}
