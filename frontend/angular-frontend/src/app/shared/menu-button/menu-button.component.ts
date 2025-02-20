import { Component, EventEmitter, Output, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-menu-button',
  templateUrl: './menu-button.component.html',
  styleUrls: ['./menu-button.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterLink],
})
export class MenuButtonComponent {
  menuVisible = false;

  @Output() toggleMenuEvent = new EventEmitter<boolean>();

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
    this.toggleMenuEvent.emit(this.menuVisible);
  }

  closeMenu() {
    this.menuVisible = false;
    this.toggleMenuEvent.emit(this.menuVisible);
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent) {
    const menuElement = document.querySelector('.menu-dropdown');
    const buttonElement = document.querySelector('.menu-button');
    if (
      menuElement &&
      !menuElement.contains(event.target as Node) &&
      buttonElement &&
      !buttonElement.contains(event.target as Node)
    ) {
      this.closeMenu();
    }
  }
}
