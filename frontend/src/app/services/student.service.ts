import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  addStudent(student: any):Observable<any> {
const apiUrl = API_URL + "/student/add-student";
    return this.http.post(apiUrl, student);
  }

  allStudent():Observable<any> {
const apiUrl = API_URL + "/student/get-all-students";
    return this.http.get(apiUrl);
  }

  getStudentById(id: number):Observable<any> {
const apiUrl = API_URL + "/student/get-student-by-id/" + id;
    return this.http.get(apiUrl);
  }

  updateStudent(student: any):Observable<any> {
const apiUrl = API_URL + "/student/update-student";
    return this.http.put(apiUrl, student);
  }

  deleteStudent(id: number):Observable<any> {
const apiUrl = API_URL + "/student/delete-student/" + id;
    return this.http.delete(apiUrl,{responseType: 'text'});
  }

}
