import { Component } from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {HttpClient, HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-welcomepage',
  standalone: true,
  imports: [HttpClientModule,
    RouterLink, RouterOutlet, RouterLinkActive],
  templateUrl: './welcomepage.component.html',
  styleUrl: './welcomepage.component.scss'
})
export class WelcomepageComponent {
  constructor(private router: Router) {}

  navigateToRegister() {
    this.router.navigate(['/register']);
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
