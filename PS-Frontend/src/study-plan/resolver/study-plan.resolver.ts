import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import {StudyPlanRestService} from "../rest/study-plan-rest.service";
import {Observable} from "rxjs";
import {inject} from "@angular/core";
import {StudyPlanQueryModel} from "../interface/study-plan-query-model";

export const StudyPlanResolver: ResolveFn<StudyPlanQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  studyPlanRestService: StudyPlanRestService = inject(StudyPlanRestService)
): Observable<StudyPlanQueryModel[]> => studyPlanRestService.getStudyPlans();
