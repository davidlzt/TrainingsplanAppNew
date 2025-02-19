import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(private router: Router) { }
  showAccount():void{
}
showAdmin():void{
  this.router.navigate(['/Admin']);
  console.log('Navigiere zum Konfigurator!');
}
  navigateHome():void{
      this.router.navigate(['/']);
    }
  navigateConfigurator(): void {
    this.router.navigate(['/configurator']);
    console.log('Navigiere zum Konfigurator!');
  }
  navigateSupport(): void {
    this.router.navigate(['/support']);
    console.log('Navigiere zum Konfigurator!');
  }
}
