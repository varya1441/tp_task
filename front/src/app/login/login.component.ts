import {Component} from '@angular/core';
import {StudentService} from "../services/student.service";
import {Router} from "@angular/router";
import {Group} from "../objects/Group";
import {Student} from "../objects/Student";

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', './util.css'],
})
export class LoginComponent{
  public login: string = "";
  public password: string = "";
  public errorState: boolean = false;

  group: Group;
  student: Student;

  constructor(private studentService: StudentService,
              private router: Router) {}

  submit() {
    this.student = this.studentService.validateStudent(this.login, this.password);
    if(this.student != null){
      this.group = this.studentService.getGroup(this.student.id);
      this.router.navigate([`/group/${this.group.groupId}`])
    }
    else{
      this.errorState = true;
    }
  }

  register(){
    this.router.navigate(['/signup'])
  }
}
