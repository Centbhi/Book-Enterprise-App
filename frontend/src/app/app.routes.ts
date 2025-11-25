import { Routes } from '@angular/router';
import { BookList } from './landing-page/landing-page';
import { Login } from './login/login';
import { GenrePage} from './genre-page/genre-page';
import { CartPage } from './cart-page/cart-page';

export const routes: Routes = [
    { path: '', component: BookList, pathMatch: 'full'},
    { path: 'login', component: Login, pathMatch: 'full'},
    { path: 'genre', component: GenrePage, pathMatch: 'full'},
    { path: 'cart', component: CartPage, pathMatch: 'full'}
];
