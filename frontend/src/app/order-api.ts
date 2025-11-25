import { Injectable } from '@angular/core';
import { Book } from './book-api';

@Injectable({
  providedIn: 'root',
})

interface OrderItem{
  book : Book,
  quantity : number
}

export interface Order{
  id: number,
  userId: number,
  // book
  totalCost: number,
  status: string,
  isFulfilled: boolean,
  orderDate: Date,
  fulfilledDate: Date,
    // private Integer id;
    // private Integer userId;
    // private Map<Book, Integer> books; //value = quantity
    // private Double totalCost;
    // private String status;
    // private Boolean isFulfilled;
    // private LocalDateTime orderDate;
    // private LocalDateTime fulfilledDate;

}

export class OrderApi {
  
}
