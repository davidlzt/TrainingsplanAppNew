import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgIf, NgForOf, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';
import {HttpClient} from '@angular/common/http';
import {TrainingsplanService} from '../../services/trainingsplan.service/trainingsplan.service.component';
import {Exercise} from '../exercises/exercises.component';
import {ExerciseService} from '../../services/exercise.service/exercise.service.component';

interface TrainingDay {
  isRest: boolean;
  selectedExercises: { [exercise: string]: boolean };
  selectedExercise?: Exercise;
}

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  standalone: true,
  imports: [NgIf, NgForOf, FormsModule, NgClass, MenuButtonComponent],
  styleUrls: ['./create-training.component.scss'],
})
export class CreateTrainingComponent implements OnInit {
  step = 1;
  trainingName: string = '';
  selectedTrainingDays: number | null = null;
  trainingWeek: TrainingDay[] = [];
  availableExercises: Exercise[] = [];
  availableMuscleGroups: string[] = ['Brust', 'Rücken', 'Schulter', 'Arme', 'Beine', 'Bauch'];
  dayNames: string[] = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag'];

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient,
  private trainingsplanService: TrainingsplanService,
  private exerciseService: ExerciseService){}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.trainingName = params['name'] || 'Dein Training';
    });
    this.loadAvailableExercises();
  }

  setTrainingDays(days: number) {
    this.selectedTrainingDays = days;
  }

  nextStep() {
    if (this.selectedTrainingDays === null) {
      return;
    }
    this.trainingWeek = this.generateTrainingWeek(this.selectedTrainingDays);
    this.step = 2;
  }

  onToStep3(){
    this.step = 3;
  }

  previousStep() {
    if (this.step === 2) {
      this.step = 1;
    } else if (this.step === 3) {
      this.step = 2;
    }
  }

  generateTrainingWeek(trainingDays: number): TrainingDay[] {
    let week: TrainingDay[] = Array(7).fill(null).map(() => ({ isRest: true, selectedExercises: {} }));

    let trainingDaysAssigned = 0;
    let dayIndex = 0;

    while (trainingDaysAssigned < trainingDays) {
      if (week[dayIndex].isRest) {
        week[dayIndex] = { isRest: false, selectedExercises: {} };
        trainingDaysAssigned++;
      }
      dayIndex = (dayIndex + 1) % 7;
    }

    return week;
  }


  saveTraining() {
    const trainingDays: number[] = this.trainingWeek
      .map((day, index) => (!day.isRest ? index : -1))
      .filter(index => index !== -1);

    const selectedExercises: { [key: number]: string } = {};
    this.trainingWeek.forEach((day, index) => {
      if (!day.isRest && day.selectedExercise) {
        selectedExercises[index] = day.selectedExercise.id;
      }
    });

    const trainingPlanData = {
      name: this.trainingName,
      description: 'Automatisch generierter Trainingsplan',
      goal: 'Muskelaufbau',
      trainingDays,
      selectedExercises
    };

    console.log('Gespeicherter Trainingsplan:', trainingPlanData);

    this.trainingsplanService.saveTrainingPlan(trainingPlanData).subscribe({
      next: response => {
        console.log('Training gespeichert!', response);
        this.router.navigate(['/dashboard']);
      },
      error: err => {
        console.error('🚨 Fehler beim Speichern des Trainings:', err);
        if (err.error) {
          console.error('👉 Server-Fehlermeldung:', err.error);
        }
      },
    });
  }

  isExerciseSelected(): boolean {
    return this.trainingWeek.some(day =>
      !day.isRest && Object.values(day.selectedExercises).some(val => val)
    );
  }

  getSelectedExercises(day: TrainingDay): string {
    return Object.keys(day.selectedExercises)
      .filter(ex => day.selectedExercises[ex])
      .join(', ') || 'Keine Übung gewählt';
  }

  loadAvailableExercises() {
    this.exerciseService.getExercises().subscribe({
      next: exercises => {
        this.availableExercises = exercises;
        console.log('Verfügbare Übungen geladen:', this.availableExercises);
      },
      error: err => {
        console.error('Fehler beim Laden der Übungen:', err);
      },
    });
  }
}
