import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../objects/Student";
import {MockServerService} from "../mock-server/mockServer.servise";

@Injectable({providedIn: "root"})
export class StudentService {
  constructor(private http: HttpClient,
              private server: MockServerService) {}

  // temporary mockServer realisation
  public validateStudent(login, password){
    return this.server.validateStudent(login, password);
  }

  // temporary mockServer realisation
  public getGroup(studentId){
    return this.server.getGroup(studentId);
  }

  public getAllStudents(groupId):Observable<Student[]>{
    return this.http.get<Student[]>("/assets/students_1.json");
  }

  // temporary mockServer realisation
  public addStudent(groupId, student){
    this.server.addStudent(groupId, student);
  }

}
