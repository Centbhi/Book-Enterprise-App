package com.hizon.controllers;

import com.hizon.model.BookDTO;
import com.hizon.service.BookService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController extends GenericController<BookDTO>{
    private final BookService service;
    public BookController(BookService service){
        super(service);
        this.service = service;
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getByGenre(@PathVariable("genre") String genre){
        return ResponseEntity.ok(service.findByGenreContaining(genre));
    }
    
}