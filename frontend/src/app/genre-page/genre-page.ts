import { Component, OnInit } from '@angular/core';
import { BooklistLayout } from "../list-container/list-container";
import { Book, BookApi } from '../api/book-api';
import { GenreService } from '../api/order-api';

@Component({
  selector: 'app-genre-page',
  imports: [BooklistLayout],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage implements OnInit{
  books:Book[] =[]
  constructor (private readonly bookApi:BookApi, public genreService:GenreService) {}

  ngOnInit(): void {
    this.findBook("ROMANCE")
  }

  findBook(genre:string): void{
    this.bookApi.getByGenre(genre).subscribe({
      next: (data) => {this.books = data;},
      error: (err) => console.error('API Error', err)
    });
  }
}

