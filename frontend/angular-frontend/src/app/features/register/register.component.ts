// register.component.ts
import { Component } from '@angular/core';
import { AuthService} from "../auth.service/auth.service.component";
import { Router } from '@angular/router';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.authService.register(this.username, this.password).subscribe(
      (response) => {
        this.router.navigate(['/login']);
      },
      (error) => {
        // handle error
      }
    );
  }
  navigateToRegister(): void {
    this.router.navigate(['/register']);
    console.log('Register');
  }
  navigateToLogin(): void {
    this.router.navigate(['/login']);
    console.log('Login');
  }
}
