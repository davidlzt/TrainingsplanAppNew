import { Component, OnInit } from '@angular/core';
import { AccountService} from '../../services/account.service/account.service.component';
import {FormsModule} from '@angular/forms';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  standalone: true,
  imports: [
    FormsModule,
    MenuButtonComponent,
    NgIf
  ],
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  user = {
    name: 'Max Mustermann',
    email: 'max@example.com',
    phone: '+49 170 1234567',
    address: 'Musterstraße 123, 12345 Musterstadt'
  };

  isEditing = false;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.loadUserData();
  }

  loadUserData() {
    this.accountService.getUserData().subscribe(data => {
      this.user = data;
    });
  }

  toggleEditMode() {
    this.isEditing = true;
  }

  saveChanges(form: any) {
    if (form.valid) {
      this.accountService.updateUserData(this.user).subscribe(response => {
        console.log('Daten erfolgreich gespeichert!', response);
        this.isEditing = false;
      });
    }
  }

  cancelEditing() {
    this.isEditing = false;
    this.loadUserData();
  }
}
