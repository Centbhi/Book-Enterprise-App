import { Component, Input} from '@angular/core';
import { Book } from '../book-api';
import { BookCard } from '../book-card/book-card'

@Component({
  selector: 'user-booklist',
  templateUrl: './user-booklist.html',
  imports: [BookCard],
  styleUrl: './booklist.css'
})

export class UserBookList{
  @Input() books: Book[] = [];
}
