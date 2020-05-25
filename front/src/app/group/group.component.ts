import {Component, OnInit, ViewChild} from '@angular/core';
import {Event} from "../objects/Event";
import {Group} from "../objects/Group";
import {Student} from "../objects/Student";
import {MatTableDataSource} from "@angular/material/table";
import {ActivatedRoute} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {StudentService} from "../services/student.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-group-component',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css'],
})
export class GroupComponent implements OnInit{
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  events: Event[];
  group: Group;
  student: Student;

  groupId: string;
  studentId: string;

  displayedColumns: string[] = ['name', 'surname'];

  dataStudentsLength = 0;
  dataStudents: MatTableDataSource<Student>;

  constructor(private activatedRoute: ActivatedRoute,
              private snackBar: MatSnackBar,
              private studentService: StudentService) {
    this.groupId = activatedRoute.snapshot.params["groupId"];
    this.dataStudents = new MatTableDataSource<Student>();
  }

  ngOnInit(){
    this.dataStudents.paginator = this.paginator;
    this.dataStudents.sort = this.sort;
    this.getAllStudents(this.groupId);
  }

  getAllStudents(groupId: string){
    this.studentService.getAllStudents(groupId).subscribe((data:Student[]) => {
      this.dataStudents.data = this.dataStudents.data.concat(data);
      this.dataStudentsLength = this.dataStudents.data.length;
      this.dataStudents._updateChangeSubscription();
    },
      error => {this.snackBar.open(error.error.message, "Ok", {duration: 8000});})
  }

  deleteStudent(studentId){
    console.log("deleted")
  }
}
