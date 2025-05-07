import { Routes } from '@angular/router';
import {WelcomepageComponent} from './core/welcomepage/welcomepage.component';
import {RegisterComponent} from './core/register/register.component';
import {LoginComponent} from './core/login/login.component';
import {DashboardComponent} from './core/dashboard/dashboard.component';
import {AdminPanelComponent} from './core/admin-panel/admin-panel.component';
import {CreateTrainingComponent} from './core/create-training/create-training.component';
import {ExercisesComponent} from './core/exercises/exercises.component';
import {AccountComponent} from './core/account/account.component';


export const routes: Routes = [
  {
    path: '',
    redirectTo: 'welcome',
    pathMatch: 'full'
  },
  {
    path: 'welcome',
    component: WelcomepageComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'admin',
    component: AdminPanelComponent,
  },
  { path: 'create-training',
    component: CreateTrainingComponent
  },
  { path: 'exercises',
    component: ExercisesComponent
  },
  { path: 'account',
    component: AccountComponent
  },
];
