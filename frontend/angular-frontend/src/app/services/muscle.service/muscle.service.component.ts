import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Muscle {
  id: number;
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class MuscleService {
  private apiUrl = 'http://localhost:8081/api/muscles';

  constructor(private http: HttpClient) {}

  getMuscles(): Observable<Muscle[]> {
    return this.http.get<Muscle[]>(this.apiUrl);
  }
}
