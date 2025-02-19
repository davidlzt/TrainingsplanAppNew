import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { WelcomepageComponent } from "./core/welcomepage/welcomepage.component";
import { LoginComponent } from "./features/login/login.component";
import { RegisterComponent } from "./features/register/register.component";
import { HomepageComponent } from "./core/homepage/homepage.component";
import { AdminPanelComponent } from "./features/admin-panel/admin-panel.component";
import { DashboardComponent } from "./features/dashboard/dashboard.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule} from '@angular/router';
import {routes} from './app-routing.module';

@NgModule({
  declarations: [

  ],
  imports: [
    RouterModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppComponent,
    WelcomepageComponent,
    HomepageComponent,
    RegisterComponent,
    LoginComponent,
    AdminPanelComponent,
    DashboardComponent,
    RouterModule.forRoot(routes),
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
