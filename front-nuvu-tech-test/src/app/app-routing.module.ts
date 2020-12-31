import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {ClientsComponent} from './components/clients/clients.component';
import {CardsComponent} from './components/cards/cards.component';
import {AuthGuard} from './_helpers/auth.guard';
import {HomeComponent} from './components/home/home.component';
import {ClientComponent} from './components/client/client.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },

  {
    path: 'home', canActivate: [AuthGuard], component: HomeComponent,
    children: [
      { path: '', canActivate: [AuthGuard], component: ClientsComponent },
      { path: 'clients', canActivate: [AuthGuard], component: ClientsComponent },
      { path: 'client/:id', canActivate: [AuthGuard], component: ClientComponent },
      { path: 'cards', canActivate: [AuthGuard], component: CardsComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
