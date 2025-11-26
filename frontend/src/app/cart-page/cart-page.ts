import { Component, OnInit } from '@angular/core';
import { Order, OrderApi } from '../order-api';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cart-page',
  imports: [DatePipe],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.css',
})
export class CartPage implements OnInit{
  constructor(private orderApi:OrderApi){};
  orders: Order[] = [];

  ngOnInit() : void{
    this.getOrders();
  }


  getOrders(){
    this.orderApi.getOrders().subscribe({
      next: (data) => {
        console.log(data);
        this.orders = data;
      },
      error: (err) => console.error('API Error', err)
    });
  }

}
