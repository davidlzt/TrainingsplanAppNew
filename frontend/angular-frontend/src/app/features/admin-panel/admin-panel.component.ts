import { Component } from '@angular/core';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { Exercise } from '../exercises/exercises.component';
import { FormsModule } from '@angular/forms';
import { MenuButtonComponent } from '../../shared/menu-button/menu-button.component';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  standalone: true,
  imports: [FormsModule, MenuButtonComponent, NgIf, NgForOf],
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {
  isFormVisible = false;
  newExercise: {
    name: string;
    description: string;
    instructions: string;
    difficulty: string;
    image: string;
    targetMuscles: string[];
    devices: string[];
  } = {
    name: '',
    description: '',
    instructions: '',
    difficulty: '',
    image: '',
    targetMuscles: [],
    devices: []
  };
  exercises: Exercise[] = [];
  showConfirmation = false;
  exerciseToRemove: Exercise | null = null;

  constructor(private exerciseService: ExerciseService) {}

  toggleForm() {
    this.isFormVisible = !this.isFormVisible;
  }

  addExercise() {
    if (this.newExercise.name && this.newExercise.description && this.newExercise.instructions) {
      this.exerciseService.addExercise(this.newExercise).subscribe((exercise) => {
        this.exercises.push(exercise);
        this.newExercise = {
          name: '',
          description: '',
          instructions: '',
          difficulty: '',
          image: '',
          targetMuscles: [],
          devices: []
        };
        this.isFormVisible = false;
      });
    }
  }

  confirmRemove(exercise: Exercise) {
    this.exerciseToRemove = exercise;
    this.showConfirmation = true;
  }

  removeExercise() {
    if (this.exerciseToRemove) {
      this.exerciseService.removeExercise(this.exerciseToRemove.id).subscribe(() => {
        this.exercises = this.exercises.filter(
          (exercise) => exercise.id !== this.exerciseToRemove?.id
        );
        this.showConfirmation = false;
        this.exerciseToRemove = null;
      });
    }
  }

  cancelRemove() {
    this.showConfirmation = false;
    this.exerciseToRemove = null;
  }

  ngOnInit() {
    this.loadExercises();
  }

  loadExercises() {
    this.exerciseService.getExercises().subscribe((data) => {
      this.exercises = data;
    });
  }
}
