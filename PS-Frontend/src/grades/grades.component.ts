import {Component, OnInit} from "@angular/core";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {SemesterGradesQueryModel} from "./interface/semester-grades-query-model";
import {ActivatedRoute} from "@angular/router";
import {GradesBannerComponent} from "./grades-table/banner/grades-banner.component";
import {GradesTableComponent} from "./grades-table/grades-table.component";

@Component({
  selector: 'grades',
  standalone: true,
  imports: [
    MatTabGroup,
    MatTab,
    CommonModule,
    MatButton,
    GradesBannerComponent,
    GradesTableComponent,
  ],
  templateUrl: './grades.component.html',
  styleUrl: './grades.component.scss'
})
export class GradesComponent implements OnInit {

  semesterGrades: SemesterGradesQueryModel[] = [];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({grades}) => {
      this.semesterGrades = grades;
    })
  }
}
