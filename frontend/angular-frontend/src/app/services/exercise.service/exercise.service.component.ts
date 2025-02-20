import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs'; // 'of' für Dummy-Daten
import { HttpClient } from '@angular/common/http';
import {Exercise} from '../../features/exercises/exercises.component';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private dummyExercises = [
    {
      id: '1',
      name: 'Bizeps Curls',
      description: 'Stärkung des Bizeps.',
      instructions: 'Stellen Sie sich aufrecht hin, halten Sie die Hanteln in beiden Händen und beugen Sie Ihre Arme, um die Hanteln zu Ihren Schultern zu bringen.'
    },
    {
      id: '1',
      name: 'Kniebeugen',
      description: 'Für starke Beine und Gesäß.',
      instructions: 'Stellen Sie sich hüftbreit auf, senken Sie Ihre Hüften, als würden Sie sich auf einen Stuhl setzen, und steigen Sie dann wieder auf.'
    },
    {
      id: '1',
      name: 'Liegestütze',
      description: 'Klassische Übung für Brust, Arme und Schultern.',
      instructions: 'Legen Sie sich auf den Bauch, stützen Sie sich auf Hände und Füße und senken Sie den Oberkörper ab, bevor Sie sich wieder nach oben drücken.'
    },
    {
      id: '1',
      name: 'Klimmzüge',
      description: 'Bauen Sie die Rücken- und Armmuskulatur auf.',
      instructions: 'Hängen Sie sich mit den Händen an eine Stange und ziehen Sie sich nach oben, bis das Kinn die Stange überquert.'
    },
    {
      id: '1',
      name: 'Plank',
      description: 'Stärkt den Kernbereich.',
      instructions: 'Stützen Sie sich auf Unterarme und Zehenspitzen, halten Sie Ihren Körper gerade und spannen Sie den Bauch an.'
    },
    {
      id: '1',
      name: 'Ausfallschritte',
      description: 'Für starke Beine und Gesäß.',
      instructions: 'Machen Sie einen großen Schritt nach vorn und senken Sie Ihr hinteres Knie in Richtung Boden, während Ihr vorderes Knie nicht über den Fuß hinausgeht.'
    }
  ];

  private apiUrl = 'http://localhost:8081/api/exercises';

  constructor(private http: HttpClient) {}

  getExercises(): Observable<any> {
    return of(this.dummyExercises);
  }
  /*getExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.apiUrl);
  }*/

  addExercise(exercise: Exercise): Observable<Exercise> {
    return this.http.post<Exercise>(this.apiUrl, exercise);
  }

  removeExercise(exerciseId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${exerciseId}`);
  }
}
