package com.hizon.entity;
import lombok.Data;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Book_data")
public class BookData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    @ElementCollection
    List<String> genre; 

    String status;
    String datePublished;
    String author;
    String publisher;
    String description;
    String coverImgUrl;

    int quantityStock;
    Double price;
    Double rating;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date lastUpdated;
}