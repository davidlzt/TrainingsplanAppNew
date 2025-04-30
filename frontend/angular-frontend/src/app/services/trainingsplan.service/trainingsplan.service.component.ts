import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Exercise} from '../../features/exercises/exercises.component';

interface TrainingPlan {
  name: string;
  description: string;
  goal: string;
  trainingDays: number[];
  exerciseIds: string[];
}

@Injectable({
  providedIn: 'root',
})
export class TrainingsplanService {
  private apiUrl = 'http://localhost:8081/api/trainingsplan';

  constructor(private http: HttpClient) {}

  saveTrainingPlan(trainingPlanData: TrainingPlan): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post(this.apiUrl, trainingPlanData, { headers });
  }

  getAllTrainingPlans(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  getTrainingFrequency(trainingsplanId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${trainingsplanId}/training-frequency`);
  }

  getExercisesForPlan(trainingsplanId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${trainingsplanId}/exercises`);
  }
  deleteTrainingPlan(planId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${planId}`);
  }

}
