import { Component } from '@angular/core';
import { AuthService } from "../../services/auth.service/auth.service.component";
import {Router, RouterLink, RouterModule} from '@angular/router';
import { FormsModule } from "@angular/forms";
import { NgIf } from "@angular/common";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  imports: [
    FormsModule,
    NgIf,
    RouterLink,
    RouterModule
  ],
  standalone: true
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  login(): void {
    const loginData = {
      username: this.username,
      password: this.password
    };

    this.authService.login(loginData).subscribe({
      next: (response) => {
        console.log("Login Response:", response);

        if (response.success) {
          console.log("Navigiere zu /dashboard");
          this.router.navigate(['/dashboard']).then(success => {
            console.log("Navigation erfolgreich:", success);
          }).catch(err => {
            console.error("Navigation fehlgeschlagen:", err);
          });
        } else {
          this.errorMessage = response.message || 'Login failed!';
        }
      },
      error: (err) => {
        console.error("Login Error:", err);
        this.errorMessage = 'Error logging in. Please check your credentials!';
      }
    });
  }
}
