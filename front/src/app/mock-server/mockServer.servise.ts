import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../objects/Student";
import {Group} from "../objects/Group";

@Injectable({providedIn: "root"})
export class MockServerService {
  constructor(private http: HttpClient) {}


}
