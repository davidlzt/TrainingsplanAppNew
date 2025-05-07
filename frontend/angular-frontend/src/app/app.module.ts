import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { WelcomepageComponent } from "./core/welcomepage/welcomepage.component";
import { LoginComponent } from "./core/login/login.component";
import { RegisterComponent } from "./core/register/register.component";
import { AdminPanelComponent } from "./core/admin-panel/admin-panel.component";
import { DashboardComponent } from "./core/dashboard/dashboard.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import {RouterModule} from '@angular/router';
import {routes} from './app.routes';
import {CreateTrainingComponent} from './core/create-training/create-training.component';
import {MenuButtonComponent} from './shared/menu-button/menu-button.component';
import {ExercisesComponent} from './core/exercises/exercises.component';
import {NgSelectModule} from '@ng-select/ng-select';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    WelcomepageComponent,
    RegisterComponent,
    LoginComponent,
    AdminPanelComponent,
    DashboardComponent,
    RouterModule.forRoot(routes),
    AppComponent,
    CreateTrainingComponent,
    MenuButtonComponent,
    ExercisesComponent,
    NgSelectModule,
  ],
  providers: [],
  bootstrap: [],
  exports: [RouterModule]
})
export class AppModule { }
