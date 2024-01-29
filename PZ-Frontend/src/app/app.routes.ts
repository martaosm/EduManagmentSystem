import {Routes} from '@angular/router';
import {StudyPlanComponent} from "../study-plan/study-plan.component";
import {StudyPlanResolver} from "../study-plan/resolver/study-plan.resolver";
import {ClassesComponent} from "../classes/classes.component";
import {ClassesResolver} from "../classes/resolver/classes.resolver";
import {SemestersComponent} from "../semesters/semesters.component";
import {CoursesResolver} from "../semesters/resolver/courses.resolver";

export const routes: Routes = [
  {path: 'study-plan', component: StudyPlanComponent, resolve: {studyPlans: StudyPlanResolver}},
  {path: 'study-plan/:studyPlanId', component: SemestersComponent, resolve: {semesters: CoursesResolver}},
  {path: 'study-plan/:studyPlanId/semester/:semester', component: ClassesComponent, resolve: {classes: ClassesResolver}},
];
