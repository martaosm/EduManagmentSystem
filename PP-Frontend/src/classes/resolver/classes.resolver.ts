import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {ClassesRestService} from "../rest/classes-rest.service";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {AuthenticationService} from "../../app/authentication.service";

export const ClassesResolver: ResolveFn<ClassesQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  classesRestService: ClassesRestService = inject(ClassesRestService),
  authenticationService: AuthenticationService = inject(AuthenticationService)
): Observable<ClassesQueryModel[]> => classesRestService.getClassesForLecturer(authenticationService.authenticatedLecturerId);
