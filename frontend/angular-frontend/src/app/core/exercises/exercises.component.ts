import { Component, OnInit } from '@angular/core';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import {NgForOf, NgIf} from '@angular/common';

export interface Exercise {
  id: string;
  name: string;
  description: string;
  instructions: string;
}

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  standalone: true,
  imports: [
    MenuButtonComponent,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./exercises.component.scss']
})
export class ExercisesComponent implements OnInit {
  exercises: Exercise[] = [];
  selectedExercise: Exercise | null = null;

  constructor(private exerciseService: ExerciseService) { }

  ngOnInit(): void {
    this.loadExercises();
  }

  loadExercises() {
    this.exerciseService.getExercises().subscribe({
      next: (data) => {
        try {
          this.exercises = Array.isArray(data) ? data : [];
        } catch (e) {
          console.error('Fehler beim Parsen der Übungen:', e);
          this.exercises = [];
        }
      },
      error: (err) => {
        console.error('Fehler beim Laden der Übungen:', err);
      }
    });
  }

  selectExercise(exercise: Exercise) {
    this.selectedExercise = exercise;
    console.log('Ausgewählte Übung:', this.selectedExercise);
  }
}
