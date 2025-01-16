import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component'; // Hier bleibt es
import { WelcomepageComponent } from "./core/welcomepage/welcomepage.component";
import { LoginComponent } from "./features/login/login.component";
import { RegisterComponent } from "./features/register/register.component";
import { HomepageComponent } from "./core/homepage/homepage.component";
import { AppRoutingModule } from './app-routing.module';
import { AdminPanelComponent } from "./features/admin-panel/admin-panel.component";
import { DashboardComponent } from "./features/dashboard/dashboard.component";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AppComponent,
    WelcomepageComponent,
    HomepageComponent,
    RegisterComponent,
    LoginComponent,
    AdminPanelComponent,
    DashboardComponent
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
