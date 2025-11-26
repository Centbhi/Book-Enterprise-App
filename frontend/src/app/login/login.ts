import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserApi, User} from '../api/user-api';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})

export class Login {

  confirmPassword = '';
  constructor (private api:UserApi,private router:Router) {}

  user: User = {
    name: '',
    password: '',
    admin: false,
    books: []
  }

  isLogin = true;

  onSubmit(){
    if(this.isLogin){
      this.api.login(this.user).subscribe({
        next: response => {
          console.log(response);
          this.api.setCurrUser(response);
          this.router.navigate(['/']);
        },
        error: err => alert(err.error.message) 
      })
    }else{
      if(this.confirmPassword!=this.user.password){
        alert('Passwords do not match');
        return;
      }else{
        this.api.createUser(this.user).subscribe({
        next: (response) => {
          alert('Successfully Registered Account:' + response.name);
          this.isLogin = true;
        },
        error: (err) => console.log('User Creation Failure:', err)
      })
      }
    }
  }
}
