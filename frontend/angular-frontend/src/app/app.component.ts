import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {WelcomepageComponent} from "./core/welcomepage/welcomepage.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {RegisterComponent} from "./features/register/register.component";
import {LoginComponent} from "./features/login/login.component";
import {AdminPanelComponent} from "./features/admin-panel/admin-panel.component";
import {DashboardComponent} from "./features/dashboard/dashboard.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WelcomepageComponent, FooterComponent, RegisterComponent, LoginComponent,AdminPanelComponent, DashboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
