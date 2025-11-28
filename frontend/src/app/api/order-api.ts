import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Book } from './book-api';

interface OrderItem{
  book: Book,
  quantity : number
}

export interface Order{
  id: number,
  userId: number,
  orders: OrderItem[],
  status: string,

  totalCost: number,
  orderDate: string,
  fulfilledDate: string
}

@Injectable({
  providedIn: 'root'
})

export class OrderStatusService{
  private readonly baseUrl = "/api/order"

  constructor(private readonly http: HttpClient) {
    this.getStatusList().subscribe(list =>{
      this.genres = list;
    })
  }
  genres : string[] = []

  getStatusList(): Observable<string[]>{
    return this.http.get<string[]>(`${this.baseUrl}/status/list`);
  }


}

@Injectable({
  providedIn: 'root',
})

export class OrderApi {
  private readonly baseUrl = "/api/order"
  constructor(private readonly http: HttpClient){}

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.baseUrl);
  }

  getOrder(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.baseUrl}/${id}`);
  }

  createOrder(Order: Order): Observable<Order> {
    return this.http.post<Order>(this.baseUrl, Order);
  }

  updateOrder(id: number, Order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.baseUrl}/${id}`, Order);
  }

  deleteOrder(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getByUser(userId: number): Observable<Order[]>{
    return this.http.get<Order[]>(`${this.baseUrl}/user/${userId}`);
  }

  getByStatus(status: string): Observable<Order[]>{
    return this.http.get<Order[]>(`${this.baseUrl}/status/${status}`);
  }

  
}
