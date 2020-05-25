import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MockServerService} from "../mock-server/mockServer.servise";

@Injectable({providedIn: "root"})
export class LoginService {
  constructor(private http: HttpClient,
              private server: MockServerService) {}

  public validateStudent(){
    return false;
  }

}
