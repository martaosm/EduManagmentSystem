import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../app/authentication.service";
import {StudentCoursesQueryModel} from "../interface/student-courses-query-model";
import {EnrollmentRestService} from "../rest/enrollment-rest.service";

export const StudentCoursesResolver: ResolveFn<StudentCoursesQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  enrollmentRestService: EnrollmentRestService = inject(EnrollmentRestService),
  authenticationService: AuthenticationService = inject(AuthenticationService),
): Observable<StudentCoursesQueryModel[]> => enrollmentRestService.getStudentCourses(authenticationService.authenticatedStudentId);
