import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient, HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-welcomepage',
  standalone: true,
  imports: [HttpClientModule  ],
  templateUrl: './welcomepage.component.html',
  styleUrl: './welcomepage.component.scss'
})
export class WelcomepageComponent {
  constructor(private router:Router, private httpClient:HttpClient, private httpClientModule:HttpClientModule) {
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
