import { Component } from '@angular/core';
import { BooklistLayout } from "../list-container/list-container";
import { Book, BookApi } from '../book-api';

@Component({
  selector: 'app-genre-page',
  imports: [BooklistLayout],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage {
  books:Book[] =[]
  constructor (private bookApi:BookApi) {}

  ngOnInit(): void {
    this.bookApi.getByGenre("Romance").subscribe({
      next: (data) => {this.books = data;},
      error: (err) => console.error('API Error', err)
    });
  }
}

