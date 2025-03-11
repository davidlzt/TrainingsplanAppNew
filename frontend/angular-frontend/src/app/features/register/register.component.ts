import { Component } from '@angular/core';
import { AuthService } from "../../services/auth.service/auth.service.component";
import { Router } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
})
export class RegisterComponent {
  id: number | undefined;
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

  ngOnInit() {
    /*this.authService.getNextId().subscribe(
      (nextId) => {
        this.id = nextId;
      },
      (error) => {
        this.errorMessage = "Error fetching ID. Try again later.";
      }
    );*/
  }

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = "Passwords do not match!";
      return;
    }

    const userData = {
      id: this.id,
      username: this.username,
      email: this.email,
      password: this.password,
      weight: this.weight,
      age: this.age,
      height: this.height,
      sex: this.sex,
      role: this.role
    };

    this.authService.register(userData).subscribe(
      () => {
        this.router.navigate(['/login']);
      },
      () => {
        this.errorMessage = 'Registration failed. Try again.';
      }
    );
  }
}
