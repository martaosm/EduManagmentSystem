import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {StudentQueryModel} from "../interface/student-query-model";
import {StudentsBannerComponent} from "./students-table/banner/students-banner.component";
import {StudentsTableComponent} from "./students-table/students-table.component";

@Component({
  selector: 'students',
  standalone: true,
  templateUrl: './students.component.html',
  imports: [
    StudentsBannerComponent,
    StudentsTableComponent
  ],
  styleUrl: './students.component.scss'
})
export class StudentsComponent {

  students: StudentQueryModel[] = [];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({students}) => {
      this.students = students;
      console.log(students)
    })
  }
}
