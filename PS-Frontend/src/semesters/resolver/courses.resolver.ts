import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {SemesterCoursesQueryModel} from "../interface/semester-courses-query-model";
import {CoursesRestService} from "../rest/courses-rest.service";

export const CoursesResolver: ResolveFn<SemesterCoursesQueryModel[]> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  coursesRestService: CoursesRestService = inject(CoursesRestService)
): Observable<SemesterCoursesQueryModel[]> => coursesRestService.getCourses(route.params['studyPlanCode']);
