import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Book {
  id?: number;
  title: string;
  genre: string[];
  status: string;
  datePublished: string;
  author: string;
  publisher: string;
  description: string;
  coverImgUrl: string;
  quantityStock: number;
  price: number;
  rating: number;
  isEditing?: boolean;
}

@Injectable({
  providedIn: 'root'
})

export class BookApi{
  private baseUrl = "/api/book"
  constructor(private http: HttpClient){}

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.baseUrl);
  }

  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }

  createBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.baseUrl, book);
  }

  updateBook(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.baseUrl}/${id}`, book);
  }

  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getByGenre(genre: string): Observable<Book[]>{
    return this.http.get<Book[]>(`${this.baseUrl}/genre/${genre}`);
  }

}