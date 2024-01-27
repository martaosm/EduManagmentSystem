import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {ClassesRestService} from "../rest/classes-rest.service";
import {ClassesQueryModel} from "../interface/classes-query-model";

export const ClassesResolver: ResolveFn<ClassesQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  classesRestService: ClassesRestService = inject(ClassesRestService)
): Observable<ClassesQueryModel[]> => classesRestService.getClasses(route.params['studyPlanId']);
