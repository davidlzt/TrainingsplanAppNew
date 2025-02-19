import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  login(loginData: { username: string, password: string }): Observable<any> {
    let username = loginData.username;
    let password = loginData.password;
    return this.http.get<any>(`${this.apiUrl}/auth/login/` +  username + "/" + password);
  }

  getNextId(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/auth/register/next-id`);
  }

  register(url: string): Observable<any> {
    return this.http.get(`${this.apiUrl}${url}`,{responseType: 'arraybuffer', headers: new HttpHeaders()});
  }
}
