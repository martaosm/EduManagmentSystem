import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {ClassesRestService} from "../rest/classes-rest.service";
import {StudentQueryModel} from "../interface/student-query-model";

export const StudentsResolver: ResolveFn<StudentQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  classesRestService: ClassesRestService = inject(ClassesRestService),
): Observable<StudentQueryModel[]> => classesRestService.getStudentsForClass(route.params['groupCode']);
