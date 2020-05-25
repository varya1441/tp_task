import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Group} from "../objects/Group";
import {Student} from "../objects/Student";
import {GroupService} from "../services/group.service";
import {StudentService} from "../services/student.service";

@Component({
  selector: 'app-signup-component',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css', './util.css'],
})
export class SignupComponent{
  login: string = "";
  password: string = "";
  name: string = "";
  surname: string = "";
  leader: boolean = false;
  inviteCode: string = "";

  errorState: boolean = false;
  errorMessage: string = "";

  group: Group = new Group();
  student: Student = new Student();

  constructor(private groupService: GroupService,
              private studentService: StudentService,
              private router: Router) {}

  validate() {
    if(this.name == "" || this.surname == "" || this.login == "" || this.password == ""){
      this.errorState = true;
      this.errorMessage = "Please, fill all fields";
      return false;
    }
    else if(this.leader == false && this.groupService.validateGroup(this.inviteCode) == null){
      this.errorState = true;
      this.errorMessage = "Invalid invite code";
      return false;
    }
    else{
      return true;
    }
  }

  submit() {
    if(this.validate()){
      this.student.name = this.name;
      this.student.lastName = this.surname;
      this.student.login = this.login;
      this.student.password = this.password;

      if(this.leader){
        this.student.role = "LEADER";
      }
      else{
        this.student.role = "STUDENT";
      }

      this.studentService.addStudent(this.inviteCode, this.student);
      this.router.navigate([''])
    }
  }
}
