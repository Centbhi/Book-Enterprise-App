import { Component, ElementRef, Input, ViewChild} from '@angular/core';
import { Book, BookApi } from '../api/book-api';
import { BookCard } from '../book-card/book-card'
import { PopupCard } from "../book-card/popup-card";
import { GenreService, Order, OrderApi } from '../api/order-api';
import { UserApi } from '../api/user-api';

@Component({
  selector: 'user-booklist',
  templateUrl: './user-booklist.html',
  imports: [BookCard, PopupCard],
  styleUrl: './booklist.css'
})

export class UserBookList{
  @ViewChild('popupRef') popupElement!: ElementRef
  @Input() books: Book[] = [];

  order: Order = {
    id: 0,
    userId: 0,
    orders: [],
    status: 'PENDING_PAYMENT',
    totalCost: 0,
    orderDate: '',
    fulfilledDate: ''
  }

  constructor(
    private readonly orderApi:OrderApi,
    private readonly userApi:UserApi, 
    private readonly bookApi:BookApi, 
    private readonly genreService:GenreService
  ){}

  selectedBook: Book | null = null
  popupAction = ''




  openPopup(book: Book, action: string) {

    this.selectedBook = book
    this.popupAction = action
    document.body.style.overflow = 'hidden'

    setTimeout(() => {
      this.popupElement?.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'center' })
    })
  }

  closePopup() {
    this.selectedBook = null
    this.popupAction = ''
    document.body.style.overflow = ''
  }

  confirmAction() {
    const userId = this.userApi.getCurrUser()?.id ?? 0
    this.order.userId = userId

    this.orderApi.createOrder(this.order).subscribe({
      error: (err) => console.log('Order Creation Failure:', err)
    })
      
    this.selectedBook = null
    this.popupAction = ''
    document.body.style.overflow = ''

  }
}
