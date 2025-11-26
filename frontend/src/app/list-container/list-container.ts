import { Component, Input } from '@angular/core';
import { UserApi } from '../api/user-api';
import { Router } from '@angular/router';
import { AdminBookList } from "../book-list/admin-booklist";
import { UserBookList } from "../book-list/user-booklist";
import { Book } from '../api/book-api';

@Component({
  selector: 'app--list-container',
  templateUrl: './list-container.html',
  styleUrl: './list-container.css',
  imports: [AdminBookList, UserBookList]
})
export class BooklistLayout {
  @Input() sectionTitle = '';
  @Input() sectionText= '';
  @Input() books: Book[] = []

  constructor (private userApi:UserApi,private router:Router) {}

  ngOnInit(): void {
    if(!this.userApi.getCurrUser()){
      alert('You must be logged in to view this page');
      this.router.navigate(['/login']);
    }
  }

  get user(){
    return this.userApi.getCurrUser();
  }

  switchAdmin(): void {
    const user = this.userApi.getCurrUser();
    if(!user||!user.id) return; 
    const updatedUser = { ...user, admin: !user.admin};
    this.userApi.updateUser(user.id, updatedUser).subscribe({
      next: updated => {this.userApi.setCurrUser(updated);
        console.log(updated);},
      error: err => console.error('Failed to update user:', err)
    });
  }
}
