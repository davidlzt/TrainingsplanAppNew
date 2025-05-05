import { Component, OnInit } from '@angular/core';
import { ExerciseService } from '../../services/exercise.service/exercise.service.component';
import {Muscle, MuscleService} from '../../services/muscle.service/muscle.service.component';
import {Device, DeviceService} from '../../services/device.service/device.service.component';
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
export class AdminPanelComponent implements OnInit {
  isFormVisible = false;
  newExercise: {
    id: string;
    name: string;
    description: string;
    targetMuscles: Muscle[];
    devices: Device[];
  } = {
    id: '',
    name: '',
    description: '',
    targetMuscles: [],
    devices: []
  };
  exercises: Exercise[] = [];
  showConfirmation = false;
  exerciseToRemove: Exercise | null = null;

  muscles: any[] = [];
  devices: any[] = [];

  constructor(
    private exerciseService: ExerciseService,
    private muscleService: MuscleService,
    private deviceService: DeviceService
  ) {}

  toggleForm() {
    this.isFormVisible = !this.isFormVisible;
  }

  addExercise() {
    if (this.newExercise.name && this.newExercise.description) {
      const exerciseToAdd = {
        id: '',
        name: this.newExercise.name,
        description: this.newExercise.description,
        targetMuscles: this.newExercise.targetMuscles.map(muscle => muscle.id),
        devices: this.newExercise.devices.map(device => device.id)
      };

      this.exerciseService.addExercise(exerciseToAdd).subscribe((exercise) => {
        this.exercises.push(exercise);
        this.newExercise = {
          id: '',
          name: '',
          description: '',
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
    this.loadMuscles();
    this.loadDevices();
  }

  loadExercises() {
    this.exerciseService.getExercises().subscribe((data) => {
      this.exercises = data;
    });
  }

  loadMuscles() {
    this.muscleService.getMuscles().subscribe((data) => {
      this.muscles = data;
    });
  }

  loadDevices() {
    this.deviceService.getDevices().subscribe((data) => {
      this.devices = data;
    });
  }
}
