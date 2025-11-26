import { Routes } from '@angular/router';
import { BookList } from './landing-page/landing-page';
import { Login } from './login/login';
import { GenrePage} from './genre-page/genre-page';
import { CartPage } from './cart-page/cart-page';
import { AuthGuard } from './auth-guard';

export const routes: Routes = [
    { path: 'login', component: Login, pathMatch: 'full'},
    { path: '', component: BookList, pathMatch: 'full', canActivate: [AuthGuard]},
    { path: 'genre', component: GenrePage, pathMatch: 'full', canActivate: [AuthGuard]},
    { path: 'cart', component: CartPage, pathMatch: 'full', canActivate: [AuthGuard]}
];
