import { Component, OnInit} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BooklistLayout } from '../list-container/list-container';
import { Book, BookApi } from '../api/book-api';

@Component({
  selector: 'app-landing-page',
  imports: [FormsModule, BooklistLayout],
  templateUrl: './landing-page.html',
  styleUrl: './landing-page.css'
})

export class BookList implements OnInit{
  books:Book[] = [];
  constructor (private readonly bookApi:BookApi) {}
  
  ngOnInit(): void {
    this.bookApi.getBooks().subscribe({
      next: (data) => {
        console.log(data);
        this.books = data;
        for(const book of this.books){
          book['isEditing'] = false
        }
      },
      error: (err) => console.error('API Error', err)
    });
  }

}