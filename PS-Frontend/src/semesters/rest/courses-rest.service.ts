import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {SemesterCoursesQueryModel} from "../interface/semester-courses-query-model";

@Injectable({
  providedIn: 'root',
})
export class CoursesRestService {

  constructor(private http: HttpClient) {
  }

  coursesUrl = 'study-plan-service.backend.svc.cluster.local:8080'

  getCourses(studyPlanCode: string): Observable<SemesterCoursesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studyPlanCode", studyPlanCode);
    return this.http.get<SemesterCoursesQueryModel[]>(this.coursesUrl + '/getAllCoursesStudyPlan', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadSemesterCoursesFromFile();
        })
      );
  }

  private loadSemesterCoursesFromFile(): Observable<SemesterCoursesQueryModel[]> {
    return this.http.get<SemesterCoursesQueryModel[]>('/assets/data/semester-courses.json')
  }
}
