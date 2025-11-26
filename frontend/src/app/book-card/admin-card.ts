import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Book } from '../api/book-api';
import { FormsModule } from '@angular/forms';
import { GenreService } from '../genre-service';

@Component({
  selector: 'app-admin-card',
  imports: [FormsModule],
  templateUrl: './admin-card.html',
  styleUrl: './book-card.css',
})
export class AdminCard {
  @Input() book!: Book;
  @Output() update = new EventEmitter<Book>();

  constructor(public genreService:GenreService){}

  validateNum(value: number, lowerLim: number = 0, upperLim: number = 9999): number{
    if(value > lowerLim){
      if(value < upperLim){
        return value;
      }else{
        return upperLim;
      }
    }else{
      return lowerLim;
    }
  }
  updateBook(){
    this.update.emit(this.book);
  }

}
