import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    NgClass,
    RouterLink,
    NgForOf,
    NgIf,
    HttpClientModule,
    RouterLink,
    RouterOutlet,
    RouterLinkActive,
    FormsModule,
    MenuButtonComponent
  ],
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit{
  currentMonth: string | undefined;
  currentYear: number | undefined;
  daysInMonth: any[] = [];
  dummyTrainings: any[] = [];
  trainingCount: number = 5;
  lastTrainingDate: string = '2025-02-18';
  averageTrainingTime: number = 60;
  trainingName: string = '';
  trainingDescription: string = '';

  constructor(private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    setTimeout(() => {
      this.currentYear = new Date().getFullYear();
      this.currentMonth = new Date().toLocaleString('default', { month: 'long' });
      this.generateCalendar();
      this.cdr.detectChanges();
    });
  }

  ngAfterViewInit() {
    this.generateCalendar();
    this.cdr.detectChanges();
  }



createTraining() {
    this.router.navigate(['/create-training'], {
      queryParams: {
        name: this.trainingName,
        description: this.trainingDescription
      }
    });
  }

  generateCalendar() {
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(today.getDate() - 1);

    let days: any[] = [];

    days.push({
      date: yesterday.getDate(),
      month: yesterday.toLocaleString('default', { month: 'long' }),
      trainings: this.dummyTrainings.filter(t => t.date === yesterday.getDate()),
      isToday: false
    });

    days.push({
      date: today.getDate(),
      month: today.toLocaleString('default', { month: 'long' }),
      trainings: this.dummyTrainings.filter(t => t.date === today.getDate()),
      isToday: true
    });

    for (let i = 1; i <= 30; i++) {
      let futureDate = new Date(today);
      futureDate.setDate(today.getDate() + i);

      days.push({
        date: futureDate.getDate(),
        month: futureDate.toLocaleString('default', { month: 'long' }),
        trainings: this.dummyTrainings.filter(t => t.date === futureDate.getDate()),
        isToday: false
      });
    }

    this.daysInMonth = days.slice(0, 28);
  }


  dayHasTraining(day: any) {
    return day.trainings.length > 0;
  }

  menuVisible = false;

  toggleMenu(visible: boolean) {
    this.menuVisible = visible;
  }
}
