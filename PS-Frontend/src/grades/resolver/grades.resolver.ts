import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {GradesRestService} from "../rest/grades-rest.service";
import {AuthenticationService} from "../../app/authentication.service";
import {SemesterGradesQueryModel} from "../interface/semester-grades-query-model";

export const GradesResolver: ResolveFn<SemesterGradesQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  gradesRestService: GradesRestService = inject(GradesRestService),
  authenticationService: AuthenticationService = inject(AuthenticationService),
): Observable<SemesterGradesQueryModel[]> => gradesRestService.getGradesForStudent(authenticationService.authenticatedStudentId);
