import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  constructor(private http: HttpClient) { }

  addSubject(subject: any): Observable<any> {
    const apiUrl = API_URL + "/subject/add-subject";
    return this.http.post(apiUrl, subject)

  }

  allSubjects(): Observable<any> {
    const apiUrl = API_URL + "/subject/get-all-subjects";
    return this.http.get(apiUrl);
  }

  getSubject(id: any): Observable<any> {
    const apiUrl = `${API_URL}/subject/get-subject-by-id/${id}`;
    return this.http.get(apiUrl);
  }



}
