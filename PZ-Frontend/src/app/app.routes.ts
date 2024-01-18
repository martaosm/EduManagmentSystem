import {Routes} from '@angular/router';
import {StudyPlanComponent} from "../study-plan/study-plan.component";
import {StudyPlanResolver} from "../study-plan/resolver/study-plan.resolver";

export const routes: Routes = [
  {path: 'study-plan', component: StudyPlanComponent, resolve: {studyPlans: StudyPlanResolver}},
];
