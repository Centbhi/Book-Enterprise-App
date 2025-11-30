import { Component, ElementRef, Input, ViewChild} from '@angular/core';
import { Book, BookApi } from '../api/book-api';
import { BookCard } from '../book-card/book-card'
import { PopupCard } from "../book-card/popup-card";
import { Order, OrderApi, OrderItem, OrderStatusService } from '../api/order-api';
import { UserApi } from '../api/user-api';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'user-booklist',
  templateUrl: './user-booklist.html',
  imports: [BookCard, PopupCard, FormsModule],
  styleUrl: './booklist.css'
})

export class UserBookList{
  @ViewChild('popupRef') popupElement!: ElementRef
  @Input() books: Book[] = [];

  order : Order = {
    id: 0,
    userId: 0,
    orders: [],
    status: 'PENDING_PAYMENT',
    totalCost: 0,
    orderDate: '',
    fulfilledDate: ''
  }

  orderItem : OrderItem = {
    book: null,
    quantity: 1
  };

  constructor(
    private readonly orderApi:OrderApi,
    private readonly userApi:UserApi, 
    private readonly bookApi:BookApi, 
    private readonly orderStatus:OrderStatusService
  ){}

  selectedBook: Book | null = null
  popupAction = ''
  isInit: boolean = false;

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
    if(this.popupAction === "buy" && this.selectedBook){
      this.selectedBook.quantityStock = this.selectedBook.quantityStock - this.orderItem.quantity
      this.bookApi.updateBook(this.selectedBook.id!,this.selectedBook).subscribe({
        next: updatedBook => {
          this.orderItem.book = updatedBook;
          this.order.status = "PROCESSING"
        },
        error: err => console.error("Update failed", err)
      })
    }
    if(this.isInit){
      this.orderApi.updateOrder(this.order.id, this.order).subscribe({
        error: (err) => console.log('Order Update Failure:', err)
      })
    }else{
      const userId = this.userApi.getCurrUser()?.id ?? 0
      this.order.userId = userId

      this.order.orders.push({ ...this.orderItem })
      this.orderApi.createOrder(this.order).subscribe({
        error: (err) => console.log('Order Creation Failure:', err)
      })
    }

      
    this.selectedBook = null
    this.popupAction = ''
    document.body.style.overflow = ''
  }
  validateNum(value: number, lowerLim: number, upperLim: number): number {
    if (value < lowerLim) return lowerLim;
    if (value > upperLim) return upperLim;
    return value;
  }
}
