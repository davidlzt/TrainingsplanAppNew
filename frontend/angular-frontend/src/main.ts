import {bootstrapApplication, BrowserModule} from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AppRoutingModule} from './app/app-routing.module';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {LoginComponent} from './app/features/login/login.component';
import {RegisterComponent} from './app/features/register/register.component';
import {WelcomepageComponent} from './app/core/welcomepage/welcomepage.component';
import {RouterLink, RouterLinkActive, RouterModule, RouterOutlet} from '@angular/router';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));

@NgModule({
  imports: [RouterModule, RouterOutlet, RouterLink, RouterLinkActive, CommonModule, BrowserModule, AppRoutingModule, AppComponent, LoginComponent, RegisterComponent, WelcomepageComponent],
  bootstrap: [],
  providers: [],
})
export class AppModule {}

platformBrowserDynamic().bootstrapModule(AppModule);
