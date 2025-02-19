import {Routes, RouterModule} from '@angular/router';
import {WelcomepageComponent} from "./core/welcomepage/welcomepage.component";
import {LoginComponent} from "./features/login/login.component";
import {RegisterComponent} from "./features/register/register.component";
import {HomepageComponent} from "./core/homepage/homepage.component";
import {AdminPanelComponent} from "./features/admin-panel/admin-panel.component";
import {NgModule} from '@angular/core';

export const routes: Routes = [
  { path: '', component: WelcomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
