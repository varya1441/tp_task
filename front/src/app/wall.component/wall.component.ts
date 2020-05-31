import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-wall-component',
  templateUrl: './wall.component.html',
  styleUrls: ['./wall.component.css'],
})
export class WallComponent{

  constructor(private router: Router) {}

  public back(){
    this.router.navigate([''])
  }
}
