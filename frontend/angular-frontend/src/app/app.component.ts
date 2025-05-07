import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive, RouterModule, RouterOutlet} from '@angular/router';
import {WelcomepageComponent} from "./core/welcomepage/welcomepage.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {RegisterComponent} from "./core/register/register.component";
import {LoginComponent} from "./core/login/login.component";
import {AdminPanelComponent} from "./core/admin-panel/admin-panel.component";
import {DashboardComponent} from "./core/dashboard/dashboard.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'TrainingsApp - WebUI';
}
