package com.hizon.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.hizon.entity.BookData;
import com.hizon.model.BookDTO;
import com.hizon.repository.BookRepository;
import com.hizon.service.BookService;

@Service
public class BookServiceImpl extends GenericServiceImpl<BookData, BookDTO> implements BookService{
    private final BookRepository repo;
    private final ModelMapper mapper;

    public BookServiceImpl(JpaRepository<BookData,Integer> repo, ModelMapper mapper){
        super(repo,mapper,BookData.class,BookDTO.class);
        this.repo = (BookRepository) repo;
        this.mapper = mapper;
    }

    @Override
    public List<BookDTO> findByGenreContaining (String genre) {
        return repo.findByGenreContaining(genre).stream()
            .map(bookData -> mapper.map(bookData, BookDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public BookDTO update(int id, BookDTO model) {
        BookData entity = repo.findById(id).orElseThrow(() ->
            new RuntimeException(BookData.class+ " not found with id: " + id));
        
        entity.getGenre().clear(); 
        //[DRAMA,FANTASY] -> [FANTASY] would instead become [FANTASY,FANTASY]
        
        mapper.map(model, entity);
        BookData saved = repo.save(entity);
        return mapper.map(saved, BookDTO.class);
    }
}
