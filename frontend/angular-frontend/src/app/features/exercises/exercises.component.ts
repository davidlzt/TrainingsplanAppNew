import { Component, OnInit } from '@angular/core';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import { NgForOf } from '@angular/common';

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
    NgForOf
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
    this.exerciseService.getExercises().subscribe(data => {
      this.exercises = data;
    });
  }

  selectExercise(exercise: Exercise) {
    this.selectedExercise = exercise;
    console.log('Ausgewählte Übung:', this.selectedExercise);
  }
}
