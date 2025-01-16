import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.scss',
  imports: [
    NgForOf
  ],
  standalone: true
})
export class AdminPanelComponent implements OnInit {
  exercises: any[] = [];
  newExercise: string = '';

  constructor(private router :Router) {}

  ngOnInit(): void {
    this.loadExercises();
  }

  loadExercises(): void {
    }

  addExercise(): void {
  }
}
