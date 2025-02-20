import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgIf, NgForOf, NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';

interface TrainingDay {
  isRest: boolean;
  selectedExercises: { [exercise: string]: boolean };
  selectedExercise?: string; // Hinzugefügt für das Dropdown
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
  availableExercises: string[] = ['Benchpress', 'Bizepscurls', 'Kniebeugen', 'Klimmzüge', 'Schulterdrücken'];
  availableMuscleGroups: string[] = ['Brust', 'Rücken', 'Schulter', 'Arme', 'Beine', 'Bauch'];
  dayNames: string[] = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag'];

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.trainingName = params['name'] || 'Dein Training';
    });
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
      this.step = 1;  // Gehe zurück zu Step 1
    } else if (this.step === 3) {
      this.step = 2;  // Gehe zurück zu Step 2
    }
  }

  generateTrainingWeek(trainingDays: number): TrainingDay[] {
    let week: TrainingDay[] = Array(7).fill(null).map(() => ({
      isRest: true,
      selectedExercises: {}
    }));

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

  saveTraining() {
    console.log('Training gespeichert:', this.trainingWeek);
    this.router.navigate(['/dashboard']);
  }
}
