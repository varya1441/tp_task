import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../objects/Student";
import {Group} from "../objects/Group";

@Injectable({providedIn: "root"})
export class MockServerService {
  constructor(private http: HttpClient) {}

  students: Student[] = [
    {
      "id": "a",
      "name": "Nikita",
      "lastName": "Tseu",
      "login": "loginTseu",
      "password": "passwordTseu",
      "role": "LEADER"
    },
    {
      "id": "b",
      "name": "Varvara",
      "lastName": "Tikhonova",
      "login": "loginTikhonova",
      "password": "passwordTikhonova",
      "role": "STUDENT"
    },
    {
      "id": "c",
      "name": "Timofey",
      "lastName": "Kruk",
      "login": "loginKruk",
      "password": "passwordKruk",
      "role": "STUDENT"
    }
  ];

  groups: Group[] = [
    {
      "groupId": "idGroup_03",
      "groupName": "group_03",
      "groupLeader": {
        "id": "a",
        "name": "Nikita",
        "lastName": "Tseu",
        "login": "loginTseu",
        "password": "passwordTseu",
        "role": "LEADER"
      },
      "students": [
        {
          "id": "a",
          "name": "Nikita",
          "lastName": "Tseu",
          "login": "loginTseu",
          "password": "passwordTseu",
          "role": "LEADER"
        },
        {
          "id": "b",
          "name": "Varvara",
          "lastName": "Tikhonova",
          "login": "loginTikhonova",
          "password": "passwordTikhonova",
          "role": "STUDENT"
        },
        {
          "id": "c",
          "name": "Timofey",
          "lastName": "Kruk",
          "login": "loginKruk",
          "password": "passwordKruk",
          "role": "STUDENT"
        }
      ]
    }
  ];

  public validateStudent(login, password){
    for(let i = 0; i < this.groups.length; i++){
      for(let j = 0; j < this.groups[i].students.length; j++){
        if(this.groups[i].students[j].login == login && this.groups[i].students[j].password == password){
          return this.groups[i].students[j];
        }
      }
    }
    return null;
  }

  public getAllStudents(groupId){
    for(let i = 0; i < this.groups.length; i++){
      if(this.groups[i].groupId == groupId){
        return this.groups[i].students;
      }
    }
    return null;
  }

  public getGroup(studentId){
    for(let i = 0; i < this.groups.length; i++){
      for(let j = 0; j < this.groups[i].students.length; j++){
        if(this.groups[i].students[j].id == studentId){
          return this.groups[i];
        }
      }
    }
    return null;
  }

  public svalidateGroup(groupId){
    for(let i = 0; i < this.groups.length; i++){
      if(this.groups[i].groupId == groupId){
        return this.groups[i];
      }
    }
    return null;
  }

  public addStudent(groupId, student){
    if(student.role == "LEADER"){
      let newGroup: Group = new Group();
      newGroup.groupId = "group".concat(student.lastName);
      newGroup.students = [student];
      newGroup.groupLeader = student;
      newGroup.groupName = "group".concat(student.lastName);
      this.groups = this.groups.concat(newGroup);
    }
    else{
      for(let i = 0; i < this.groups.length; i++){
        if(this.groups[i].groupId == groupId){
          this.groups[i].students = this.groups[i].students.concat(student);
        }
      }
    }
  }
}
