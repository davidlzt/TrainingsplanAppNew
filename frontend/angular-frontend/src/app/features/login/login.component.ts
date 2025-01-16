// login.component.ts
import { Component } from '@angular/core';
import { AuthService} from "../auth.service/auth.service.component";
import { Router } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  imports: [
    FormsModule,
    NgIf
  ],
  standalone: true
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private httpClient:HttpClient) {}

  login(): void {
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        this.errorMessage = 'Invalid login credentials';
      }
    );
  }
}
