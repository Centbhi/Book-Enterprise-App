import { Injectable } from '@angular/core';
import { Book } from './book-api';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface User{
  id?: number;
  name: string;
  admin: boolean;
  password: string;
  books: Book[];
}

@Injectable({
  providedIn: 'root'
})
export class UserApi {

  private baseUrl = "/api/user"
  private currUser: User|null = null;
  constructor(private http: HttpClient){}


  logout(): void{
    localStorage.removeItem('currUser');
    this.currUser = null;
  }

  getCurrUser(): User|null{
    if(!this.currUser){
      const userJson = localStorage.getItem('currUser');
      if (userJson) this.currUser = JSON.parse(userJson);
      else this.currUser = null;
    }
    return this.currUser;
  }

  getCurrUserSafe(): User{
    const user = this.getCurrUser();
    if (!user) throw new Error('User not authenticated');
    return user;
  }

  setCurrUser(user: User): void {
    this.currUser=user;
    localStorage.setItem('currUser', JSON.stringify(user));
  }

  login(user: User): Observable<User>{
    let req = {name:user.name, password:user.password};
    return this.http.post<User>(`${this.baseUrl}/login`,req);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUserByName(name: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${name}`);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/${id}`, user);
  }

  deleteUser(name: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${name}`);
  }
  
}
