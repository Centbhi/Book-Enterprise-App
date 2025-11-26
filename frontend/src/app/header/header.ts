import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { UserApi } from '../api/user-api';

@Component({
  selector: 'app-header',
  imports: [RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})

export class Header {
constructor(private readonly router:Router, private readonly api:UserApi){}
  confirmLogout(event: Event){
    event.preventDefault();
    if(globalThis.confirm('Are you sure you want to logout?')){
      this.api.logout();
      this.router.navigate(['/login'])
    }
  }
}
