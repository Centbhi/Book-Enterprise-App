import { Component, ElementRef, Input, ViewChild} from '@angular/core';
import { Book } from '../api/book-api';
import { BookCard } from '../book-card/book-card'

@Component({
  selector: 'user-booklist',
  templateUrl: './user-booklist.html',
  imports: [BookCard],
  styleUrl: './booklist.css'
})

export class UserBookList{
  @ViewChild('popupRef') popupElement!: ElementRef
  @Input() books: Book[] = [];
  selectedBook: Book | null = null
  popupAction = ''

  openPopup(book: Book, action: string) {
    this.selectedBook = book
    this.popupAction = action

    setTimeout(() => {
      this.popupElement?.nativeElement.scrollIntoView({ behavior: 'smooth' })
    })
  }

  closePopup() {
    this.selectedBook = null
    this.popupAction = ''
  }
}
