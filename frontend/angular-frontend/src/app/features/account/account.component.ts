import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account.service/account.service.component';
import { FormsModule } from '@angular/forms';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  standalone: true,
  imports: [FormsModule, MenuButtonComponent, NgIf, NgForOf],
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  users: any[] = [];
  selectedUserId: number | null = null;
  selectedUser: any = null;
  isEditing = false;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  // Lädt alle Benutzer vom Backend
  loadUsers() {
    this.accountService.getUsers().subscribe(users => {
      console.log('Geladene Benutzer:', users);
      this.users = users;
    });
  }

  // Beim Klick auf einen Benutzer wird dessen ID gesetzt und die Daten werden als Kopie in selectedUser abgelegt
  toggleDropdown(userId: number | string) {
    const numericId = Number(userId);
    if (this.selectedUserId === numericId) {
      // Schließen: Dropdown ausblenden und Daten zurücksetzen
      this.selectedUserId = null;
      this.selectedUser = null;
      this.isEditing = false;
    } else {
      // Öffnen: Setze die ausgewählte ID und kopiere die Benutzerdaten
      this.selectedUserId = numericId;
      const user = this.users.find(u => Number(u.id) === numericId);
      this.selectedUser = user ? { ...user } : null;
      this.isEditing = false;
    }
  }

  // Wechselt in den Bearbeitungsmodus
  toggleEditMode() {
    this.isEditing = !this.isEditing;
  }

  // Speichert Änderungen an den Benutzerdaten
  saveChanges(form: any) {
    if (form.valid && this.selectedUserId !== null) {
      this.accountService.updateUserData(this.selectedUser).subscribe(response => {
        console.log('Daten erfolgreich gespeichert!', response);
        // Aktualisiere die Liste, damit auch die Anzeige up-to-date ist
        this.loadUsers();
        this.isEditing = false;
      });
    }
  }

  // Bricht die Bearbeitung ab und lädt die ursprünglichen Daten erneut
  cancelEditing() {
    this.isEditing = false;
    if (this.selectedUserId !== null) {
      const user = this.users.find(u => Number(u.id) === this.selectedUserId);
      if (user) {
        this.selectedUser = { ...user };
      }
    }
  }
}
