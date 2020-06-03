import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-wall-component',
  templateUrl: './wall.component.html',
  styleUrls: ['./wall.component.css'],
})
export class WallComponent{

  message: string = "";

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute) {
    if(activatedRoute.snapshot.routeConfig.path == "notconfirmed"){
      this.message = "Sorry, your membership application is still pending :(";
    }
    else{
      this.message = "Congrats! Now wait until your application will be confirmed by group leader";
    }
  }

  public back(){
    this.router.navigate([''])
  }
}
