
import { Component } from '@angular/core';
import { AuthService} from "../auth.service/auth.service.component";
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {FormsModule} from "@angular/forms";
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  standalone: true,
  imports: [
    FormsModule,
    RouterOutlet,
    CommonModule,
    RouterLink
  ],
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = "Passwords do not match!";
      return;
    }

    this.authService.register(this.username, this.password).subscribe(
      () => {
        this.router.navigate(['/login']);
      },
      (error) => {
        this.errorMessage = 'Registration failed. Try again.';
      }
    );
  }
}
