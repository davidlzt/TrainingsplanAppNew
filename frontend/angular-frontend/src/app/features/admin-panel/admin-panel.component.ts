import { Component } from '@angular/core';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import { Exercise } from '../exercises/exercises.component';
import {FormsModule} from '@angular/forms';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  standalone: true,
  imports: [
    FormsModule,
    MenuButtonComponent,
    NgIf,
    NgForOf
  ],
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {
  isFormVisible = false;
  newExercise: Exercise = { id:'', name: '', description: '', instructions: '' };
  exercises: Exercise[] = [];

  constructor(private exerciseService: ExerciseService) {}

  toggleForm() {
    this.isFormVisible = !this.isFormVisible;
  }

  addExercise() {
    if (this.newExercise.name && this.newExercise.description && this.newExercise.instructions) {
      this.exerciseService.addExercise(this.newExercise).subscribe((exercise) => {
        this.exercises.push(exercise); // Neue Übung zur Liste hinzufügen
        this.newExercise = { id: '',name: '', description: '', instructions: '' };
        this.isFormVisible = false;
      });
    }
  }

  showAllExercises() {
    this.exerciseService.getExercises().subscribe(data => {
      this.exercises = data;
    });
  }

  removeExercise(exerciseId: string) {
    this.exerciseService.removeExercise(exerciseId).subscribe(() => {
      this.exercises = this.exercises.filter(exercise => exercise.id !== exerciseId);
    });
  }

  ngOnInit() {
    this.loadExercises();
  }

  loadExercises() {
    this.exerciseService.getExercises().subscribe(data => {
      this.exercises = data;
    });
  }
}
