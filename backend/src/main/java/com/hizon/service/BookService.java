package com.hizon.service;

import java.util.List;

import com.hizon.model.BookDTO;

public interface BookService extends GenericService<BookDTO>{
    List<BookDTO> findByGenreContaining(String genre);
}
