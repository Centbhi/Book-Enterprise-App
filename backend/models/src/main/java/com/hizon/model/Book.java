package com.hizon.model;
import java.util.List;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String title;
    private List<String> genre;
    private String status;
    private String author;
    private String publisher;
    private String description;
    private String coverImgUrl;

    private int quantityStock;
    private Double price;
    private Double rating;
}
