package com.hizon.service;

import java.util.List;

import com.hizon.model.Book;

public interface BookService extends GenericService<Book>{
    List<Book> findByGenreContaining(String genre);
}
