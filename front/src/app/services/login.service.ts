import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MockServerService} from "../mock-server/mockServer.servise";
import * as jwt_decode from 'jwt-decode';

@Injectable({providedIn: "root"})
export class LoginService {
  constructor(private http: HttpClient,
              private server: MockServerService) {}

  public validateStudent(rlogin, rpassword){
    return this.http.post("http://localhost:8080/auth/login", { login: rlogin, password: rpassword });
  }

}
