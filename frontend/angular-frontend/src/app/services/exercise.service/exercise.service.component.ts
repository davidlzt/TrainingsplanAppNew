import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Exercise } from '../../features/exercises/exercises.component';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private apiUrl = 'http://localhost:8081/api/exercises';

  constructor(private http: HttpClient) {}

  getExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.apiUrl);
  }

  addExercise(exercise: { instructions: string; name: string; description: string }): Observable<Exercise> {
    return this.http.post<Exercise>(this.apiUrl, exercise);
  }

  removeExercise(exerciseId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${exerciseId}`);
  }
}
