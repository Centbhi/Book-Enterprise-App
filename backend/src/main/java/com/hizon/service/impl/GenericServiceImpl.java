package com.hizon.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hizon.service.GenericService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<ModelData,Model> implements GenericService<Model>{
    
    private final JpaRepository<ModelData, Integer> repo;
    private final ModelMapper mapper;
    private final Class<ModelData> modelDataClass;
    private final Class<Model> modelClass;

    @Override
    public Model create(Model model){
        ModelData entity = mapper.map(model, modelDataClass);
        ModelData saved = repo.save(entity);
        return mapper.map(saved,modelClass);
    }

    @Override
    public Model read(Integer id){
        ModelData entity = repo.findById(id)
        .orElseThrow(() -> new RuntimeException(modelDataClass.getSimpleName() + "not found with id: " + id));
        return mapper.map(entity, modelClass);
    }

    @Override
    public List<Model> readAll(){
        return repo.findAll().stream()
            .map(entity -> mapper.map(entity, modelClass))
            .collect(Collectors.toList());
    }

    @Override
    public Model update(int id,Model model){

        ModelData entity = repo.findById(id).orElseThrow(()->
            new RuntimeException(modelDataClass.getSimpleName() + " not found with id: " + id));
        mapper.map(model,entity);
        ModelData saved = repo.save(entity);
        return mapper.map(saved,modelClass);
    }

    @Override
    public void delete(Integer id){
        if(!repo.existsById(id)){
            throw new RuntimeException("Model not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
