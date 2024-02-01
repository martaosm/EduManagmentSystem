import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {SemesterCoursesQueryModel} from "../interface/semester-courses-query-model";
import {GeneralEducationCourseQueryModel} from "../interface/general-education-course-query-model";
import {AddCourseToStudyPlanCommand} from "../courses-table/command/add-course-to-study-plan.command";

@Injectable({
  providedIn: 'root',
})
export class CoursesRestService {

  constructor(private http: HttpClient) {
  }

  coursesUrl = 'http://study-plan-service.backend.svc.cluster.local:8080'

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

  getGeneralEducationCourses(): Observable<GeneralEducationCourseQueryModel[]> {
    return this.http.get<GeneralEducationCourseQueryModel[]>(this.coursesUrl+'/getAllCourses')
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadGeneralEducationCoursesFromFile();
        })
      )
  }

  addGeneralEducationCourseToStudyPlan(addCourseToStudyPlanCommand: AddCourseToStudyPlanCommand) {
    //TODO WB: integration with backend
    // post
    // url/addCourseToStudyPlan
  }

  private loadSemesterCoursesFromFile(): Observable<SemesterCoursesQueryModel[]> {
    return this.http.get<SemesterCoursesQueryModel[]>('/assets/data/semester-courses.json')
  }

  private loadGeneralEducationCoursesFromFile() {
    return this.http.get<GeneralEducationCourseQueryModel[]>('/assets/data/general-education-courses.json');
  }
}
