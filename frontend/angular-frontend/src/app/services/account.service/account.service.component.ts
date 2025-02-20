import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:8081/api/account';

  constructor(private http: HttpClient) { }

  getUserData(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  updateUserData(user: any): Observable<any> {
    return this.http.put<any>(this.apiUrl, user);
  }
}
