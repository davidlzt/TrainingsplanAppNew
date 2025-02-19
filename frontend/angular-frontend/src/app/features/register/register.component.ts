import { Component } from '@angular/core';
import { AuthService } from "../auth.service/auth.service.component";
import {Router, RouterLink} from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    RouterLink
  ],
})
export class RegisterComponent {
  id: number = 0;
  username: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  weight: number = 0;
  age: number = 0;
  height: number = 0;
  sex: string = 'male';
  role: string = 'USER';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = "Passwords do not match!";
      return;
    }

    this.authService.getNextId().subscribe(
      (nextId) => {
        this.id = nextId;

        const url = `/register/${this.id}/${this.username}/${this.email}/${this.password}/${this.weight}/${this.age}/${this.height}/${this.sex}/${this.role}`;

        this.authService.register(url).subscribe(
          () => {
            this.router.navigate(['/login']);
          },
          (error) => {
            this.errorMessage = 'Registration failed. Try again.';
          }
        );
      },
      (error) => {
        this.errorMessage = "Error fetching ID. Try again later.";
      }
    );
  }
}
