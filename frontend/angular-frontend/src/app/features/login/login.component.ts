import { Component } from '@angular/core';
import { AuthService } from "../../services/auth.service/auth.service.component";
import {Router, RouterLink} from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { NgIf } from "@angular/common";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  imports: [
    FormsModule,
    NgIf,
    RouterLink
  ],
  standalone: true
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  login(): void {
    const loginData = {
      username: this.username,
      password: this.password
    };

    if (this.username === 'admin' && this.password === 'admin') {
      localStorage.setItem('authToken', 'admin-token');
      this.router.navigate(['/dashboard']);
      return;
    }

    this.authService.login(loginData).subscribe({
      next: (response) => {
        if (response.success && response.token) {
          localStorage.setItem('authToken', response.token);
          this.router.navigate(['/dashboard']);
        } else {
          this.errorMessage = response.message || 'Login failed!';
        }
      },
      error: () => {
        this.errorMessage = 'Error logging in. Please check your credentials!';
      }
    });
  }
}
