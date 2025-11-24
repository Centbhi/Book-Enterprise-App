package com.hizon.controllers;

import com.hizon.model.Book;
import com.hizon.service.BookService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController extends GenericController<Book>{
    private final BookService service;
    public BookController(BookService service){
        super(service);
        this.service = service;
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getByGenre(@PathVariable("genre") String genre){
        return ResponseEntity.ok(service.findByGenreContaining(genre));
    }
    
}