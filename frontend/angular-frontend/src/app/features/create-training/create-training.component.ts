import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { Exercise } from '../exercises/exercises.component';
import { FormsModule } from '@angular/forms';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';

interface TrainingDay {
  isRest: boolean;
  selectedExercises: { [exercise: string]: boolean };
}

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.scss'],
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    NgClass,
    MenuButtonComponent
  ],
  standalone: true
})
export class CreateTrainingComponent implements OnInit {
  step = 1;
  selectedTrainingDays: number = 0;
  selectedTrainingDaysList: number[] = [];
  availableExercises: Exercise[] = [];
  availableMuscleGroups: string[] = ['Brust', 'Rücken', 'Schulter', 'Arme', 'Beine', 'Bauch'];
  dayNames: string[] = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag'];
  selectedExercises: { [day: number]: { [muscle: string]: { [exerciseId: string]: boolean } } } = {};
  selectedMuscleGroups: { [day: number]: { [muscle: string]: boolean } } = {};

  constructor(private exerciseService: ExerciseService, private router: Router) {}

  ngOnInit() {
    this.loadAvailableExercises();
  }

  setTrainingDays(days: number) {
    this.selectedTrainingDays = days;
    this.selectedTrainingDaysList = [];
    this.step = 2;
  }

  toggleTrainingDay(dayIndex: number) {
    if (this.selectedTrainingDaysList.includes(dayIndex)) {
      this.selectedTrainingDaysList = this.selectedTrainingDaysList.filter(day => day !== dayIndex);
    } else {
      if (this.selectedTrainingDaysList.length < this.selectedTrainingDays) {
        this.selectedTrainingDaysList.push(dayIndex);
      }
    }
  }

  nextStep() {
    if (this.step === 2) {
      this.selectedTrainingDaysList.forEach(day => {
        this.selectedMuscleGroups[day] = this.availableMuscleGroups.reduce((acc, muscle) => {
          acc[muscle] = false;
          return acc;
        }, {} as { [muscle: string]: boolean });

        this.selectedExercises[day] = this.availableMuscleGroups.reduce((acc, muscle) => {
          acc[muscle] = this.availableExercises.reduce((exerciseAcc, exercise) => {
            exerciseAcc[exercise.id] = false;
            return exerciseAcc;
          }, {} as { [exerciseId: string]: boolean });
          return acc;
        }, {} as { [muscle: string]: { [exerciseId: string]: boolean } });
      });

      if (this.availableExercises.length === 0) {
        this.loadAvailableExercises().then(() => {
          this.step = 3;
        });
      } else {
        this.step = 3;
      }
    } else if (this.step === 3) {
      this.step = 4;
    }
  }



  loadAvailableExercises(): Promise<void> {
    return new Promise((resolve, reject) => {
      this.exerciseService.getExercises().subscribe({
        next: exercises => {
          this.availableExercises = exercises;
          console.log('Verfügbare Übungen geladen:', this.availableExercises);
          resolve();
        },
        error: err => {
          console.error('Fehler beim Laden der Übungen:', err);
          reject(err);
        },
      });
    });
  }

  saveTraining() {
    const trainingPlanData = {
      selectedTrainingDays: this.selectedTrainingDaysList,
      selectedMuscleGroups: this.selectedMuscleGroups,
      selectedExercises: this.selectedExercises
    };

    console.log('Training gespeichert:', trainingPlanData);
    console.log(this.selectedExercises);
    this.router.navigate(['/dashboard']);
  }
}
