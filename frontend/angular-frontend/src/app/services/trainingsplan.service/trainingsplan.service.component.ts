import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface TrainingPlan {
  name: string;
  description: string;
  goal: string;
  trainingDays: number[];
  selectedExercises: { [key: number]: string };
}

@Injectable({
  providedIn: 'root',
})
export class TrainingsplanService {
  private apiUrl = 'http://localhost:8081/api/trainingsplan';

  constructor(private http: HttpClient) {}

  saveTrainingPlan(trainingPlanData: TrainingPlan): Observable<any> {
    return this.http.post(this.apiUrl, trainingPlanData);
  }
}
