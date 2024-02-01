import {Component, OnInit} from "@angular/core";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {ActivatedRoute} from "@angular/router";
import {StudentCoursesQueryModel} from "./interface/student-courses-query-model";
import {GradesBannerComponent} from "./banner/enrollment-banner.component";
import {EnrollmentClassesTableComponent} from "./enrollment-classes-table/enrollment-classes-table.component";

@Component({
  selector: 'enrollment',
  standalone: true,
  imports: [
    MatTabGroup,
    MatTab,
    CommonModule,
    MatButton,
    GradesBannerComponent,
    EnrollmentClassesTableComponent,
  ],
  templateUrl: './enrollment.component.html',
  styleUrl: './enrollment.component.scss'
})
export class EnrollmentComponent implements OnInit {

  studentCourses: StudentCoursesQueryModel[] = [];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({studentCourses}) => {
      this.studentCourses = studentCourses;
    })
  }
}
