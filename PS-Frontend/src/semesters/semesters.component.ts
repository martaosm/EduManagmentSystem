import {Component, OnInit, ViewChild} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {SemesterCoursesQueryModel} from "./interface/semester-courses-query-model";
import {CoursesTableComponent} from "./courses-table/courses-table.component";
import {CommonModule} from "@angular/common";
import {CoursesBannerComponent} from "./courses-table/banner/courses-banner.component";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'semesters',
  standalone: true,
  imports: [
    CoursesBannerComponent,
    MatTabGroup,
    MatTab,
    CoursesTableComponent,
    CommonModule,
    MatButton,
  ],
  templateUrl: './semesters.component.html',
  styleUrl: './semesters.component.scss'
})
export class SemestersComponent implements OnInit {

  @ViewChild('courseTable') courseTable!: CoursesTableComponent;

  semesters: SemesterCoursesQueryModel[] = [];

  studyPlanCode!: string | null;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({semesters}) => {
      this.semesters = semesters;
    })
    this.studyPlanCode = this.activatedRoute.snapshot.paramMap.get('studyPlanCode');
  }

  onShowClassesClicked(semester: number) {
    this.router.navigate(['study-plans/' + this.studyPlanCode + '/semester/' + semester])
  }
}
