import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service/auth.service.component';
import {Router, RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import { trigger, transition, style, animate } from '@angular/animations';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  animations: [
    trigger('buttonAnimation', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('0.5s', style({ opacity: 1 }))
      ]),
      transition(':leave', [
        style({ opacity: 1 }),
        animate('0.5s', style({ opacity: 0 }))
      ])
    ])
  ],
  imports: [
    FormsModule,
    NgIf,
    RouterLink
  ],
  styleUrls: ['./register.component.scss']
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

  registerFields: any[] = [];

  constructor(private authService: AuthService, private router: Router) {
    this.initFields();
  }
  register(): void {
    if (this.password !== this.confirmPassword) {
      this.errorMessage = "Passwords do not match!";
      return;
    }
    const formData = {
      username: this.username,
      email: this.email,
      password: this.password,
      weight: this.weight,
      age: this.age,
      height: this.height,
      sex: this.sex,
      role: this.role
    };
    console.log('Form data before sending:', formData);
    this.authService.register(formData).subscribe(response => {
      console.log('Registration successful:', response);
    }, error => {
      this.errorMessage = 'Registration failed!';
      console.error('Registration error:', error);
    });
    this.router.navigate(['/login']);
  }

  initFields() {
    this.registerFields = [
      {
        id: 'id',
        name: 'id',
        label: 'User ID',
        type: 'text',
        model: this.id,
        readonly: true,
        icon: 'fas fa-id-card'
      },
      {
        id: 'username',
        name: 'username',
        label: 'Username',
        type: 'text',
        model: this.username,
        required: true,
        icon: 'fas fa-user'
      },
      {
        id: 'email',
        name: 'email',
        label: 'Email',
        type: 'email',
        model: this.email,
        required: true,
        icon: 'fas fa-envelope'
      },
      {
        id: 'password',
        name: 'password',
        label: 'Passwort',
        type: 'password',
        model: this.password,
        required: true,
        icon: 'fas fa-lock'
      },
      {
        id: 'confirmPassword',
        name: 'confirmPassword',
        label: 'Passwort bestätigen',
        type: 'password',
        model: this.confirmPassword,
        required: true,
        icon: 'fas fa-lock'
      },
      {
        id: 'weight',
        name: 'weight',
        label: 'Gewicht (kg)',
        type: 'number',
        model: this.weight,
        required: true,
        icon: 'fas fa-weight'
      },
      {
        id: 'age',
        name: 'age',
        label: 'Alter',
        type: 'number',
        model: this.age,
        required: true,
        icon: 'fas fa-birthday-cake'
      },
      {
        id: 'height',
        name: 'height',
        label: 'Größe (cm)',
        type: 'number',
        model: this.height,
        required: true,
        icon: 'fas fa-ruler-vertical'
      }
    ];
  }

}
