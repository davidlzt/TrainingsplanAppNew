import { Routes } from '@angular/router';
import {WelcomepageComponent} from './core/welcomepage/welcomepage.component';
import {RegisterComponent} from './features/register/register.component';
import {LoginComponent} from './features/login/login.component';
import {DashboardComponent} from './features/dashboard/dashboard.component';
import {AdminPanelComponent} from './features/admin-panel/admin-panel.component';
import {CreateTrainingComponent} from './features/create-training/create-training.component';
import {ExercisesComponent} from './features/exercises/exercises.component';
import {AccountComponent} from './features/account/account.component';


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
