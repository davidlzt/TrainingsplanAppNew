import {Routes} from '@angular/router';
import {WelcomepageComponent} from "./core/welcomepage/welcomepage.component";
import {LoginComponent} from "./features/login/login.component";
import {RegisterComponent} from "./features/register/register.component";
import {HomepageComponent} from "./core/homepage/homepage.component";
import {AdminPanelComponent} from "./features/admin-panel/admin-panel.component";

export const routes: Routes = [
  { path: '', component: WelcomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'admin', component: AdminPanelComponent },
  { path: '**', redirectTo: '' },
];
export class RoutingModule { }
