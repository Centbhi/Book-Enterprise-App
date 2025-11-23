package com.hizon.serviceImpl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.hizon.entity.BookData;
import com.hizon.model.Book;
import com.hizon.repository.BookRepository;
import com.hizon.service.BookService;

@Service
public class BookServiceImpl extends GenericServiceImpl<BookData, Book> implements BookService{
    private final BookRepository repo;
    private final ModelMapper mapper;

    public BookServiceImpl(JpaRepository<BookData,Integer> repo, ModelMapper mapper){
        super(repo,mapper,BookData.class,Book.class);
        this.repo = (BookRepository) repo;
        this.mapper = mapper;
    }

    @Override
    public List<Book> findByGenreContaining (String genre) {
        return repo.findByGenreContaining(genre).stream()
            .map(bookData -> mapper.map(bookData, Book.class))
            .collect(Collectors.toList());
    }

    @Override
    public Book update(int id, Book model) {
        BookData entity = repo.findById(id).orElseThrow(() ->
            new RuntimeException(BookData.class.getSimpleName() + " not found with id: " + id));
        
        entity.getGenre().clear(); 
        //[DRAMA,FANTASY] -> [FANTASY] would instead become [FANTASY,FANTASY]
        
        mapper.map(model, entity);
        BookData saved = repo.save(entity);
        return mapper.map(saved, Book.class);
    }
}
