import { Component, OnInit } from '@angular/core';
import { Order, OrderApi } from '../api/order-api';
import { DatePipe } from '@angular/common';
import { UserApi } from '../api/user-api';

@Component({
  selector: 'app-cart-page',
  imports: [DatePipe],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.css',
})
export class CartPage implements OnInit{
  constructor(private readonly orderApi:OrderApi,private readonly userApi:UserApi){};
  orders: Order[] = [];

  ngOnInit() : void{
    this.getOrders();
  }

  getOrders(){
    this.orderApi.getByUser(this.userApi.getCurrUserSafe().id!).subscribe({
      next: (data) => {
        console.log(data);
        this.orders = data;
      },
      error: (err) => console.error('API Error', err)
    });
  }

}
