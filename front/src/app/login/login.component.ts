import {Component} from '@angular/core';
import {StudentService} from "../services/student.service";
import {Router} from "@angular/router";
import {Group} from "../objects/Group";
import {Student} from "../objects/Student";
import {LoginService} from "../services/login.service";
import * as jwt_decode from 'jwt-decode';
import {Token} from "../objects/token";

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', './util.css'],
})
export class LoginComponent{
  public login: string = "";
  public password: string = "";
  public role = "";

  public errorState: string = "";

  group: Group;
  student: Student;

  constructor(private studentService: StudentService,
              private loginService: LoginService,
              private router: Router) {}

  submit() {
    this.loginService.validateStudent(this.login, this.password).subscribe(
      (token:Token) => {
      this.password = "";
      this.login = jwt_decode(token.accessToken).sub;

      this.studentService.getStudentByLogin(this.login).subscribe((stud:Student) => {
        this.student = stud;
        if(this.student.checkedInvite == true){
          this.router.navigate([`/main/${jwt_decode(token.accessToken).sub}`]);
        }
        else{
          this.router.navigate([`/notconfirmed`]);
        }
        this.student = null;
        this.login = "";
      });

    },
        error => {
      if(error.error.status === 403){
        this.errorState = "Invalid username or password";
      }
      else if(this.login == "" || this.password == ""){
        this.errorState = "Please, fill all fields";
      }
      else{
        this.errorState = "Sorry, something gone wrong :(";
      }
      this.password = "";
      this.login = "";
    });
  }

  register(){
    this.router.navigate(['/signup'])
  }
}
