import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { Exercise } from '../exercises/exercises.component';
import { FormsModule } from '@angular/forms';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import {TrainingsplanService} from '../../services/trainingsplan.service/trainingsplan.service.component';

interface TrainingPlan {
  name: string;
  description: string;
  goal: string;
  trainingDays: number[];
  exerciseIds: string[];


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
  trainingName: string = '';
  trainingDescription: string = '';

  constructor(private exerciseService: ExerciseService, private router: Router, private trainingplanService: TrainingsplanService) {}

  ngOnInit() {
    this.loadAvailableExercises();

    this.selectedTrainingDaysList.forEach(day => {
      this.selectedExercises[day] = this.selectedExercises[day] || {};

      this.availableMuscleGroups.forEach(muscle => {
        this.selectedExercises[day][muscle] = {};
      });
    });
  }

  setTrainingDays(days: number) {
    this.selectedTrainingDays = days;
    this.selectedTrainingDaysList = [];
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
    if (this.step === 1) {
      if (!this.trainingName || !this.trainingDescription || !this.selectedTrainingDays) {
        alert("Bitte gib einen Trainingsnamen, eine Beschreibung und die Anzahl der Trainingstage an.");
        return;
      }
    }

    if (this.step === 2) {
      this.selectedTrainingDaysList.forEach(day => {
        if (!this.selectedMuscleGroups[day]) {
          this.selectedMuscleGroups[day] = this.availableMuscleGroups.reduce((acc, muscle) => {
            acc[muscle] = false;
            return acc;
          }, {} as { [muscle: string]: boolean });
        }

        if (!this.selectedExercises[day]) {
          this.selectedExercises[day] = this.availableMuscleGroups.reduce((acc, muscle) => {
            acc[muscle] = {};
            return acc;
          }, {} as { [muscle: string]: { [exerciseId: string]: boolean } });
        }
      });
    }

    if (this.step === 3) {
      this.step = 4;
    } else {
      this.step++;
    }
  }

  areMuscleGroupsSelected(): boolean {
    for (let day of this.selectedTrainingDaysList) {
      if (!this.selectedMuscleGroups[day] || !Object.values(this.selectedMuscleGroups[day]).includes(true)) {
        return false;
      }
    }
    return true;
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
    const exercisesToSave: string[] = [];

    for (const [day, muscles] of Object.entries(this.selectedExercises)) {
      for (const [muscle, exercises] of Object.entries(muscles)) {
        for (const [exerciseId, selected] of Object.entries(exercises)) {
          if (selected) {
            // @ts-ignore
            const exerciseIds = this.availableExercises.find(e => e.id === +exerciseId);
            if (exerciseIds) {
              exercisesToSave.push(exerciseIds.id);
            }
          }
        }
      }
    }

    const trainingPlanData: TrainingPlan = {
      name: this.trainingName,
      description: this.trainingDescription,
      goal: "Muskelaufbau",
      trainingDays: this.selectedTrainingDaysList,
      exerciseIds: exercisesToSave,
    };

    console.log('Training speichern:', trainingPlanData);

    this.trainingplanService.saveTrainingPlan(trainingPlanData).subscribe({
      next: (response) => {
        console.log('Trainingsplan erfolgreich gespeichert:', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Fehler beim Speichern des Trainingsplans:', error);
      },
    });
  }

}
