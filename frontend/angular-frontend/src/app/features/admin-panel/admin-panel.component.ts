import { Component, OnInit } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.scss',
    imports: [
        NgForOf,
        NgIf,
        RouterLink
    ],
  standalone: true
})
export class AdminPanelComponent implements OnInit {
  exercises: any[] = [];
  newExercise: string = '';
  menuVisible = false;

  constructor(private router :Router) {}

  ngOnInit(): void {
    this.loadExercises();
  }

  loadExercises(): void {
    }

  addExercise(): void {
  }
  toggleMenu() {
    this.menuVisible = !this.menuVisible;
  }
}
