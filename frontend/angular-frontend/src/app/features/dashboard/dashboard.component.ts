import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {TrainingsplanService} from '../../services/trainingsplan.service/trainingsplan.service.component';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';
import {FormsModule} from '@angular/forms';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    NgClass,
    NgForOf,
    MenuButtonComponent,
    NgIf,
    FormsModule
  ],
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit {
  currentMonth: string | undefined;
  currentYear: number | undefined;
  daysInMonth: any[] = [];
  dummyTrainings: any[] = [];
  trainingCount: number = 5;
  lastTrainingDate: string = '2025-02-18';
  averageTrainingTime: number = 60;
  trainingName: string = '';
  trainingDescription: string = '';
  trainingPlans: any[] = [];
  daysOfWeek: string[] = ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"];
  trainingFrequency: number = 0;
  exercises: any[] = [];

  constructor(
    private router: Router,
    private cdr: ChangeDetectorRef,
    private trainingsplanService: TrainingsplanService,
    private http: HttpClient,
  ) {}

  ngOnInit() {
    setTimeout(() => {
      this.currentYear = new Date().getFullYear();
      this.currentMonth = new Date().toLocaleString('default', { month: 'long' });
      this.generateCalendar();
      this.cdr.detectChanges();
      this.fetchTrainingPlans();
      const trainingsplanId = 1;
      this.loadTrainingDetails(trainingsplanId);
    });
  }

  ngAfterViewInit() {
    this.generateCalendar();
    this.cdr.detectChanges();
  }

  fetchTrainingPlans(): void {
    this.trainingsplanService.getAllTrainingPlans().subscribe(
      (data) => {
        console.log('Trainingspläne erhalten:', data);
        this.trainingPlans = data;
      },
      (error) => {
        console.error('Fehler beim Abrufen der Trainingspläne:', error);
      }
    );
  }
  formatTrainingDays(trainingDays: number[]): string {
    return trainingDays.map(day => this.daysOfWeek[day]).join(', ');
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
  loadTrainingDetails(trainingsplanId: number) {
    this.trainingsplanService.getTrainingFrequency(trainingsplanId).subscribe((frequency: number) => {
      this.trainingFrequency = frequency;
    });

    this.trainingsplanService.getExercisesForPlan(trainingsplanId).subscribe((exercises: any[]) => {
      this.exercises = exercises;
    });
  }
}
