import { Component, Input } from '@angular/core';
import { Book } from '../api/book-api';

@Component({
  selector: 'app-popup-card',
  imports: [],
  templateUrl: './popup-card.html',
  styleUrl: './popup-card.css'
})
export class PopupCard{
  @Input() book!: Book;

}
