// dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  imports: [
    NgForOf
  ],
  standalone: true
})
export class DashboardComponent implements OnInit {
  trainings: any[] = [];

  constructor() {}

  ngOnInit(): void {
  }
  toggleMenu(): void {}
}
