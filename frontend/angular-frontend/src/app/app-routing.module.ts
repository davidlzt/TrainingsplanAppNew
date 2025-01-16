import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {WelcomepageComponent} from "./core/welcomepage/welcomepage.component";
import {LoginComponent} from "./features/login/login.component";
import {RegisterComponent} from "./features/register/register.component";
import {HomepageComponent} from "./core/homepage/homepage.component";

const routes: Routes = [
  { path: '', component: WelcomepageComponent },
  { path: '/', component: WelcomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomepageComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
