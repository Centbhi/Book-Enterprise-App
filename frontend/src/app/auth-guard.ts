import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { UserApi } from './api/user-api';

@Injectable({
  providedIn: 'root',
})

export class AuthGuard implements CanActivate{
  constructor (private readonly userApi:UserApi,private readonly router:Router) {}
  canActivate() : boolean | UrlTree{
    if(this.userApi.getCurrUser()){
      return true;
    }
    alert('You must be logged in to view this page');
    return this.router.parseUrl('/login');
  }
}
