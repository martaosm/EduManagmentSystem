import {Routes} from '@angular/router';
import {StudyPlanResolver} from "../study-plan/resolver/study-plan.resolver";
import {StudyPlanComponent} from "../study-plan/study-plan.component";
import {SemestersComponent} from "../semesters/semesters.component";
import {CoursesResolver} from "../semesters/resolver/courses.resolver";
import {ClassesComponent} from "../classes/classes.component";
import {ClassesResolver} from "../classes/resolver/classes.resolver";
import {GradesComponent} from "../grades/grades.component";
import {GradesResolver} from "../grades/resolver/grades.resolver";
import {StudentCoursesResolver} from "../enrollment/resolver/student-courses.resolver";
import {EnrollmentComponent} from "../enrollment/enrollment.component";

export const routes: Routes = [
  {path: 'study-plans', component: StudyPlanComponent, resolve: {studyPlans: StudyPlanResolver}},
  {path: 'study-plans/:studyPlanCode', component: SemestersComponent, resolve: {semesters: CoursesResolver}},
  {
    path: 'study-plans/:studyPlanCode/semester/:semester',
    component: ClassesComponent,
    resolve: {classes: ClassesResolver}
  },
  {path: 'grades', component: GradesComponent, resolve: {grades: GradesResolver}},
  {path: 'enrollment', component: EnrollmentComponent, resolve: {studentCourses: StudentCoursesResolver}},
];
