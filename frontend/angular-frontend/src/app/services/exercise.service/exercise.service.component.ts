import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Exercise } from '../../core/exercises/exercises.component';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private apiUrl = 'http://localhost:8081/api/exercises';

  constructor(private http: HttpClient) {}

  getExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.apiUrl);
  }

  addExercise(exercise: {id:string; name: string; description: string }): Observable<Exercise> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post<any>(this.apiUrl, exercise, { headers });
  }

  removeExercise(exerciseId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${exerciseId}`);
  }
}
