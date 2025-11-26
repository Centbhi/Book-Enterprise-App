import { Component } from '@angular/core';
import { BooklistLayout } from "../list-container/list-container";
import { Book, BookApi } from '../api/book-api';
import { GenreService } from '../api/genre-service';

@Component({
  selector: 'app-genre-page',
  imports: [BooklistLayout],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage {
  books:Book[] =[]
  constructor (private bookApi:BookApi, public genreService:GenreService) {}

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

