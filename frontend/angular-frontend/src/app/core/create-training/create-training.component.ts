import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { Exercise } from '../exercises/exercises.component';
import { FormsModule } from '@angular/forms';
import {KeyValuePipe, NgClass, NgForOf, NgIf} from '@angular/common';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import {TrainingsplanService} from '../../services/trainingsplan.service/trainingsplan.service.component';
import {Muscle, MuscleService} from '../../services/muscle.service/muscle.service.component';
import {NgSelectComponent} from '@ng-select/ng-select';

interface TrainingPlan {
  name: string;
  description: string;
  goal: string,
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
    MenuButtonComponent,
    NgSelectComponent,
    KeyValuePipe,
  ],
  standalone: true
})
export class CreateTrainingComponent implements OnInit {
  step = 1;
  selectedTrainingDays: number = 0;
  selectedTrainingDaysList: number[] = [];
  availableExercises: Exercise[] = [];
  availableMuscleGroups: string[] = [];
  allMuscles: Muscle[] = [];
  dayNames: string[] = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag'];
  selectedExercises: { [day: number]: { [muscle: string]: { [exerciseId: string]: boolean } } } = {};
  trainingStrategy: string[] = ["Muskelaufbau", "Ganzkörper", "Fettabbau"];
  selectedStrategy: string = this.trainingStrategy[0];
  selectedMuscleGroups: { [day: number]: { [muscle: string]: boolean } } = {};
  trainingName: string = '';
  trainingDescription: string = '';

  constructor(private exerciseService: ExerciseService, private router: Router, private trainingplanService: TrainingsplanService,
              private muscleService: MuscleService,) {}

  ngOnInit() {
    this.loadAvailableExercises().then(() => {
      this.loadAvailableMuscles();
    });
    this.loadAvailableMuscles();

    this.selectedTrainingDaysList.forEach(day => {
      if (!this.selectedExercises[day]) {
        this.selectedExercises[day] = {};
      }

      this.availableMuscleGroups.forEach(muscle => {
        if (!this.selectedExercises[day][muscle]) {
          this.selectedExercises[day][muscle] = {};
        }
      });

      if (!this.selectedMuscleGroups[day]) {
        this.selectedMuscleGroups[day] = this.availableMuscleGroups.reduce((acc, muscle) => {
          acc[muscle] = false;
          return acc;
        }, {} as { [muscle: string]: boolean });
      }
    });
  }

  setTrainingDays(days: number) {
    this.selectedTrainingDays = days;
    this.selectedTrainingDaysList = [];
  }

  loadAvailableMuscles() {
    this.muscleService.getMuscles().subscribe(
      (muscles) => {
        this.allMuscles = muscles;
        this.availableMuscleGroups = muscles.map(m => m.name);
        console.log('Verfügbare Übungen geladen:', this.availableMuscleGroups);
      },
      (error) => {
        console.error('Fehler beim Laden der Muskelgruppen', error);
      }
    );
  }
  toggleTrainingDay(dayIndex: number) {
    if (this.selectedTrainingDaysList.includes(dayIndex)) {
      this.selectedTrainingDaysList = this.selectedTrainingDaysList.filter(day => day !== dayIndex);
    } else {
      if (this.selectedTrainingDaysList.length < this.selectedTrainingDays) {
        this.selectedTrainingDaysList.push(dayIndex);

        if (!this.selectedExercises[dayIndex]) {
          this.selectedExercises[dayIndex] = {};
        }

        for (const muscle of this.availableMuscleGroups) {
          if (!this.selectedExercises[dayIndex][muscle]) {
            this.selectedExercises[dayIndex][muscle] = {};
          }
        }

        if (!this.selectedMuscleGroups[dayIndex]) {
          this.selectedMuscleGroups[dayIndex] = this.availableMuscleGroups.reduce((acc, muscle) => {
            acc[muscle] = false;
            return acc;
          }, {} as { [muscle: string]: boolean });
        }
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

    if (this.step === 2 && this.selectedTrainingDaysList.length !== this.selectedTrainingDays) {
      alert("Bitte wähle die Tage aus, an denen du trainieren möchtest.");
      return;
    }

    if (this.step === 3 && !this.areMuscleGroupsSelected()) {
      alert("Bitte wähle Muskelgruppen für jeden Trainingstag aus.");
      return;
    }

    if (this.step === 4 && !this.areExercisesSelected()) {
      alert("Bitte wähle Übungen für die ausgewählten Muskelgruppen aus.");
      return;
    }

    this.step++;
  }

  areMuscleGroupsSelected(): boolean {
    for (let day of this.selectedTrainingDaysList) {
      const selected = this.selectedMuscleGroups[day] as unknown as string[];
      if (!selected || selected.length === 0) {
        return false;
      }
    }
    return true;
  }

  areExercisesSelected(): boolean {
    for (let day of this.selectedTrainingDaysList) {
      if (this.selectedMuscleGroups[day]) {
        for (let muscle of Object.keys(this.selectedMuscleGroups[day])) {
          if (this.selectedMuscleGroups[day][muscle]) {
            if (!this.selectedExercises[day][muscle] || Object.keys(this.selectedExercises[day][muscle]).length === 0) {
              return false;
            }
          }
        }
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
      goal: this.selectedStrategy,
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
