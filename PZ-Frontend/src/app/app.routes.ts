import {Routes} from '@angular/router';
import {StudyPlanComponent} from "../study-plan/study-plan.component";
import {StudyPlanResolver} from "../study-plan/resolver/study-plan.resolver";
import {ClassesComponent} from "../classes/classes.component";
import {ClassesResolver} from "../classes/resolver/classes.resolver";

export const routes: Routes = [
  {path: 'study-plan', component: StudyPlanComponent, resolve: {studyPlans: StudyPlanResolver}},
  {path: 'classes/:studyPlanId', component: ClassesComponent, resolve: {classes: ClassesResolver}},
];
