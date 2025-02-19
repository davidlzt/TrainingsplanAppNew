import { Component, OnInit } from '@angular/core';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    NgClass,
    RouterLink,
    NgForOf,
    NgIf,HttpClientModule,
    RouterLink, RouterOutlet, RouterLinkActive
  ],
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  currentMonth: string | undefined;
  currentYear: number | undefined;
  daysInMonth: any[] = [];
  dummyTrainings: any[] = [];
  menuVisible = false;
  trainingCount: number = 5;
  lastTrainingDate: string = '2025-02-18';
  averageTrainingTime: number = 60;

  constructor(private router: Router) {}

  ngOnInit() {
    this.currentYear = new Date().getFullYear();
    this.currentMonth = new Date().toLocaleString('default', { month: 'long' });
    this.generateCalendar(new Date().getMonth(), this.currentYear);
    this.generateDummyTrainings();
  }

  generateDummyTrainings() {
    this.dummyTrainings = [
      { date: 5, details: 'Leg Day' },
      { date: 12, details: 'Cardio' },
      { date: 18, details: 'Chest Day' },
      { date: 22, details: 'Back Day' },
      { date: 28, details: 'Yoga' }
    ];
  }

  generateCalendar(month: number, year: number) {
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const numberOfDays = lastDay.getDate();
    this.daysInMonth = [];

    for (let i = 1; i <= numberOfDays; i++) {
      let date = new Date(year, month, i);
      this.daysInMonth.push({
        date: i,
        trainings: this.dummyTrainings.filter(t => t.date === i)
      });
    }
  }

  dayHasTraining(day: any) {
    return day.trainings.length > 0;
  }

  previousMonth() {
    let currentMonth = new Date(`${this.currentMonth} 1, ${this.currentYear}`);
    currentMonth.setMonth(currentMonth.getMonth() - 1);
    this.currentMonth = currentMonth.toLocaleString('default', { month: 'long' });
    this.currentYear = currentMonth.getFullYear();
    this.generateCalendar(currentMonth.getMonth(), this.currentYear);
  }

  nextMonth() {
    let currentMonth = new Date(`${this.currentMonth} 1, ${this.currentYear}`);
    currentMonth.setMonth(currentMonth.getMonth() + 1);
    this.currentMonth = currentMonth.toLocaleString('default', { month: 'long' });
    this.currentYear = currentMonth.getFullYear();
    this.generateCalendar(currentMonth.getMonth(), this.currentYear);
  }

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
  }
}
