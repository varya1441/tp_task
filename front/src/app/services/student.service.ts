import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MockServerService} from "../mock-server/mockServer.servise";

@Injectable({providedIn: "root"})
export class StudentService {
  constructor(private http: HttpClient,
              private server: MockServerService) {}

  public getGroupByLogin(studentLogin){
    return this.http.get('http://localhost:8080/student/group/' + studentLogin);
  }

  public getStudentByLogin(studentLogin){
    return this.http.get('http://localhost:8080/student/' + studentLogin);
  }

  public addStudent(inviteCode, groupName, student){
    let leader: boolean = (student.role == "LEADER");
    return this.http.post('http://localhost:8080/auth/register/', {
      login: student.login,
      password: student.password,
      name: student.name,
      lastName: student.lastName,
      groupName: groupName,
      inviteCode: inviteCode,
      leader: leader
    });
  }

  public deleteStudent(studentLogin){
    return this.http.delete('http://localhost:8080/student/' + studentLogin);
  }

  public confirmxStudent(studentLogin){
    return this.http.put('http://localhost:8080/student/' + studentLogin, {});
  }

}
