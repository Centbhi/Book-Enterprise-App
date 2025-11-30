import { Component, Input } from '@angular/core';
import { UserApi } from '../api/user-api';
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

  constructor (private readonly userApi:UserApi) {}

  get user(){
    return this.userApi.getCurrUser();
  }

  switchAdmin(): void {
    const user = this.userApi.getCurrUserSafe();
    const updatedUser = { ...user, admin: !user.admin};
    this.userApi.updateUser(user.id!, updatedUser).subscribe({
      next: updated => {this.userApi.setCurrUser(updated);
        console.log(updated);},
      error: err => console.error('Failed to update user:', err)
    });
  }
}
