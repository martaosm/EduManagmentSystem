import {Component, inject, OnInit, ViewChild} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {SemesterCoursesQueryModel} from "./interface/semester-courses-query-model";
import {CoursesTableComponent} from "./courses-table/courses-table.component";
import {CommonModule} from "@angular/common";
import {CoursesBannerComponent} from "./courses-table/banner/courses-banner.component";
import {MatButton} from "@angular/material/button";
import {MatDialog} from "@angular/material/dialog";
import {
  AddGeneralEducationCourseDialogComponent
} from "./courses-table/dialog/add-general-education-course-dialog/add-general-education-course-dialog.component";
import {AddCourseToStudyPlanCommand} from "./courses-table/command/add-course-to-study-plan.command";
import {CoursesRestService} from "./rest/courses-rest.service";
import {GeneralEducationCourseQueryModel} from "./interface/general-education-course-query-model";

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

  studyPlanId!: string | null;

  private dialog: MatDialog = inject(MatDialog);
  private coursesRestService: CoursesRestService = inject(CoursesRestService);

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({semesters}) => {
      this.semesters = semesters;
    })
    this.studyPlanId = this.activatedRoute.snapshot.paramMap.get('studyPlanId');
  }

  onShowClassesClicked(semester: number) {
    this.router.navigate(['study-plan/' + this.studyPlanId + '/semester/' + semester])
  }

  onAddCourseClicked() {
    this.coursesRestService.getGeneralEducationCourses().subscribe((courses: GeneralEducationCourseQueryModel[]) => {
      const dialogRef = this.dialog.open(AddGeneralEducationCourseDialogComponent, {
        width: '500px',
        height: 'auto',
        data: {
          selectedOption: '',
          options: courses,
        },
      });

      dialogRef.afterClosed().subscribe(selectedCourse => {
        if (selectedCourse) {
          const studyPlanId = this.activatedRoute.snapshot.paramMap.get('studyPlanId');
          const addCourseToStudyPlanCommand = {
            courseId: selectedCourse.courseId,
            studyPlanId: studyPlanId,
            semester: 1 // TODO WB: get selected semester
          } as AddCourseToStudyPlanCommand;
          this.coursesRestService.addGeneralEducationCourseToStudyPlan(addCourseToStudyPlanCommand)
        }
      });
    });
  }
}
