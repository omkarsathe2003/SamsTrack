import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {

  constructor(private userService: UserService, private router: Router) { }

  user = {
    username: '',
    password: '',
  };

  login() {

  this.userService.login(this.user).subscribe((res: any) => {

    // Store JWT token
    localStorage.setItem('token', res.token);

    // Get logged-in user details
    this.userService.getCurrentUser().subscribe((user) => {

      localStorage.setItem('user', user.username);
      localStorage.setItem('role', user.role);

      if (user.role === 'admin') {
        this.router.navigateByUrl('admin-dashboard');
      } else {
        this.router.navigateByUrl('faculty-dashboard');
      }

    });

  });

}
}
