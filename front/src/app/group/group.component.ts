import {Component, OnInit, ViewChild} from '@angular/core';
import {Event} from "../objects/Event";
import {Group} from "../objects/Group";
import {Student} from "../objects/Student";
import {MatTableDataSource} from "@angular/material/table";
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {StudentService} from "../services/student.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EventService} from "../services/event.service";
import {MatMenuTrigger} from "@angular/material/menu";
import {EventCreateComponent} from "../event-create/event-create.component";
import {MatDialog} from "@angular/material/dialog";
import {TransferService} from "../services/transfer.service";
import {EventEditComponent} from "../event-edit/event-edit.component";

@Component({
  selector: 'app-group-component',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css'],
})
export class GroupComponent implements OnInit{
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatMenuTrigger) contextMenu: MatMenuTrigger;

  contextMenuPosition = { x: '0px', y: '0px' };

  student: Student;
  group: Group;
  events: Event[];

  dataStudents: MatTableDataSource<Student>;
  dataStudentsLength = 0;
  displayedColumnsStudents: string[] = ['name', 'surname'];

  dataNotConfirmed: MatTableDataSource<Student>;
  dataNotConfirmedLength = 0;
  displayedColumnsNotConfirmed: string[] = ['name', 'surname', 'actions'];

  dataEvents: MatTableDataSource<Event>;
  dataEventsLength = 0;
  displayedColumnsEvents: string[] = ['name', 'date', 'actions'];

  panelOpenState = false;

  constructor(private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar,
              private router: Router,
              private studentService: StudentService,
              private eventService: EventService,
              private transferService: TransferService,
              private dialog: MatDialog) {

    this.student = null;
    this.dataStudents = new MatTableDataSource<Student>();
    this.dataNotConfirmed = new MatTableDataSource<Student>();
    this.dataEvents = new MatTableDataSource<Event>();

    let login = activatedRoute.snapshot.params["studentLogin"];

    this.studentService.getStudentByLogin(login).subscribe((stud:Student) => {
      this.student = stud;
    });

    this.studentService.getGroupByLogin(login).subscribe((gr: Group) => {
      this.group = gr;
      this.getAllStudents();
      this.getAllEvents(this.group.groupName);
    });
  }

  ngOnInit(){
    this.dataStudents.paginator = this.paginator;
    this.dataStudents.sort = this.sort;
  }

  getAllStudents(){
    for(let i = 0; i < this.group.students.length; i++){
      if(this.group.students[i].checkedInvite == true){
        this.dataStudents.data = this.dataStudents.data.concat(this.group.students[i]);
        this.dataStudentsLength = this.dataStudents.data.length;
      }
      else{
        this.dataNotConfirmed.data = this.dataNotConfirmed.data.concat(this.group.students[i]);
        this.dataNotConfirmedLength = this.dataNotConfirmed.data.length;
      }
    }
    this.dataNotConfirmed.data = this.sortBySurname(this.dataNotConfirmed.data);
    this.dataStudents.data = this.sortBySurname(this.dataStudents.data);
    this.dataNotConfirmed._updateChangeSubscription();
    this.dataStudents._updateChangeSubscription();
  }

  getAllEvents(groupName){
    this.eventService.getAllEventsForGroup(groupName).subscribe((eventList: Event[]) => {
      this.dataEvents.data = this.dataEvents.data.concat(eventList);
      this.dataEvents.data = this.sortByDate(this.dataEvents.data);
      this.dataEventsLength = this.dataEvents.data.length;
      this.dataEvents._updateChangeSubscription();
    });
  }

  deleteStudent(studentLogin){
    this.studentService.deleteStudent(studentLogin).subscribe(response => {
        console.log(response);
        this.dataStudents.data = this.dataStudents.data.filter((element:Student) => (element.login != studentLogin));
        this.dataStudents.data = this.sortBySurname(this.dataStudents.data);
        this.dataStudentsLength = this.dataStudents.data.length;
        this.dataStudents._updateChangeSubscription();
        this.dataNotConfirmed.data = this.dataNotConfirmed.data.filter((element:Student) => (element.login != studentLogin));
        this.dataNotConfirmed.data = this.sortBySurname(this.dataNotConfirmed.data);
        this.dataNotConfirmedLength = this.dataNotConfirmed.data.length;
        this.dataNotConfirmed._updateChangeSubscription();
      },
      error => {this.snackBar.open(error.error.message, "Ok", {duration: 8000});});
  }

  confirmxStudent(student){
    student.checkedInvite = true;
    this.studentService.confirmxStudent(student.login).subscribe(data => {
        this.dataStudents.data = this.dataStudents.data.concat(student);
        this.dataStudents.data = this.sortBySurname(this.dataStudents.data);
        this.dataStudentsLength = this.dataStudents.data.length;
        this.dataStudents._updateChangeSubscription();
        this.dataNotConfirmed.data = this.dataNotConfirmed.data.filter((element:Student) => (element.login != student.login));
        this.dataNotConfirmed.data = this.sortBySurname(this.dataNotConfirmed.data);
        this.dataNotConfirmedLength = this.dataNotConfirmed.data.length;
        this.dataNotConfirmed._updateChangeSubscription();
    },
        error => {this.snackBar.open(error.error.message, "Ok", {duration: 8000});});
  }

  addEvent(){
    let i = 0;
    let dialogRef = this.dialog.open(EventCreateComponent);
    dialogRef.afterClosed().subscribe(data => {
      if(data != "cancelled"){
        if(i === 0){
          this.eventService.createxEvent(this.group.groupName).subscribe(response => {
              this.dataEvents.data = [];
              this.getAllEvents(this.group.groupName);
              i++;
            },
            error => {this.snackBar.open(error.error.message, "Ok", {duration: 8000});
            });
        }
      }
    });
  }

  getInviteCode(){
    this.snackBar.open("Invite code was copied to clipboard", "Ok", {duration: 1200});
  }

  onContextMenu(event: MouseEvent, item: Event) {
    event.preventDefault();
    this.contextMenuPosition.x = event.clientX + 'px';
    this.contextMenuPosition.y = event.clientY + 'px';
    this.contextMenu.menuData = { 'item': item };
    this.contextMenu.menu.focusFirstItem('mouse');
    this.contextMenu.openMenu();
  }

  onContextMenuEditEvent(item: Event) {
    this.eventService.selectedEvent.date = item.date;
    this.eventService.selectedEvent.name = item.eventName;
    let i = 0;
    let dialogRef = this.dialog.open(EventEditComponent);
    dialogRef.afterClosed().subscribe(data => {
      if(data != "cancelled"){
        if(i === 0){
          this.eventService.updatexEvent(item.eventName).subscribe(response => {
              this.dataEvents.data = [];
              this.getAllEvents(this.group.groupName);
              i++;
            },
            error => {this.snackBar.open(error.error.message, "Ok", {duration: 8000});
            });
        }
      }
    });
  }

  onContextMenuGenerateResult(item: Event) {
    this.eventService.generateResult(item.eventName, this.group.groupName);
  }

  onContextMenuUpdatePrior(item: Event) {
    this.router.navigate([`/prior/${item.eventName}/${this.student.login}`]);
  }

  showResult(){
    //CODE
  }

  sortBySurname(arr){
    return arr.sort((obj1, obj2) => {
      if (obj1.lastName > obj2.lastName) {
        return 1;
      }
      if (obj1.lastName < obj2.lastName) {
        return -1;
      }
      return 0;
    });
  }

  sortByDate(arr){
    return arr.sort((obj1, obj2) => {
      if (obj1.date > obj2.date) {
        return 1;
      }
      if (obj1.date < obj2.date) {
        return -1;
      }
      return 0;
    });
  }
}
