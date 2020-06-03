import {Component} from '@angular/core';
import {Priority} from "../objects/Priority";
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../services/event.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {StudentService} from "../services/student.service";
import {Group} from "../objects/Group";

@Component({
  selector: 'app-prior',
  templateUrl: 'prior.component.html',
  styleUrls: ['prior.component.css'],
})
export class PriorComponent {

  priorities:Priority = new Priority();
  values: any[] = [];

  login = "";
  eventName = "";
  length = 0;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private eventService: EventService,
              private studentService: StudentService,
              private snackBar: MatSnackBar) {
    this.login = activatedRoute.snapshot.params["studentLogin"];
    this.eventName = activatedRoute.snapshot.params["eventName"];

    this.eventService.getxPriority(this.eventName, this.login).subscribe((data: any) => {
      this.priorities.priority = [];
      let i = 1;
      while(data.priorities[i] != undefined){
        this.priorities.priority.push(data.priorities[i]);
        i++;
      }
      this.length = this.priorities.priority.length;
        for(let i = 0; i < this.length; i++){
          this.values.push({val:this.priorities.priority[i]});
          console.log(this.values);
        }

        this.studentService.getGroupByLogin(this.login).subscribe((gr: Group) => {
          this.length = gr.students.length;
          if(this.priorities.priority.length < this.length){
            for(let i = 0; i < this.length - this.priorities.priority.length; i++){
              this.values.push({val:1});
            }
          }
        });
    },
      error => {
      if(error.error.message.slice(0, 5) == "prior"){
        this.studentService.getGroupByLogin(this.login).subscribe((gr: Group) => {
          this.length = gr.students.length;
          for(let i = 0; i < this.length; i++){
            this.values.push({val:3});
          }
        });
      }
      else{
        this.snackBar.open(error.error.message, "Ok", {duration: 8000});
      }
    });
  }

  save(){
    this.priorities.priority = [];
    for(let i = 0; i < this.length; i++){
      this.priorities.priority[i] = this.values[i].val;
    }
    this.eventService.setxPriority(this.login, this.eventName, this.priorities.priority);
    this.snackBar.open("Priorities successfully updated", "Ok", {duration: 1200});
  }

  cancel(){
    this.router.navigate([`/main/${this.login}`]);
  }

}
