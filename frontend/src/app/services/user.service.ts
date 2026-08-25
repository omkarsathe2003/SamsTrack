import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../config/api.config';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(user: any): Observable<any> {
    const apiUrl = API_URL + "/authenticate";

    return this.http.post(apiUrl, user);
  }

  getCurrentUser(): Observable<any> {
  const apiUrl = API_URL + "/user/me";
  return this.http.get(apiUrl);
}

  isLoggedIn(): boolean {
    return !!localStorage.getItem('user'); // or any other logic
  }




  registerUser(user: any): Observable<any> {
    const apiUrl = API_URL + "/user/register-user";

    return this.http.post(apiUrl, user, { 'responseType': 'text' });

  }


  getAllUser(): Observable<any> {
    const apiUrl = API_URL + "/user/get-all-user";
    return this.http.get(apiUrl);
  }

  getAllAdmins(): Observable<any> {
    const apiUrl = API_URL + "/user/get-all-admin";
    return this.http.get(apiUrl);
  }

  getAllFaculty(): Observable<any> {
    const apiUrl = API_URL + "/user/get-all-faculty";
    return this.http.get(apiUrl);
  }

  deleteUser(username: string): Observable<any> {
    const apiUrl = `${API_URL}/user/delete-user-by-username?username=${username}`;
    return this.http.delete(apiUrl, { 'responseType': 'text' })
  }

  getUserByUsername(username: string): Observable<any> {
    const apiUrl = `${API_URL}/user/get-user-by-username/${username}`;

    return this.http.get(apiUrl);
  }


  updateUser(user: any): Observable<any> {
    const apiUrl = API_URL + "/user/update-user";
    return this.http.put(apiUrl, user)
  }

}
