import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {StudentCoursesQueryModel} from "../interface/student-courses-query-model";
import {ClassesQueryModel} from "../../classes/interface/classes-query-model";
import {EnrollToClassCommand} from "../enrollment-classes-table/command/enroll-to-class.command";

@Injectable({
  providedIn: 'root',
})
export class EnrollmentRestService {

  enrollmentUrl = 'enrollment-service.backend.svc.cluster.local:8080'

  constructor(private http: HttpClient) {
  }

  getStudentCourses(studentIndex: string): Observable<StudentCoursesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studentIndex", studentIndex);
    return this.http.get<StudentCoursesQueryModel[]>(this.enrollmentUrl + '/getCoursesForStudent', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudentCoursesFromFile();
        })
      );
  }

  getClassesByCourseCode(courseCode: string): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("courseCode", courseCode);
    return this.http.get<ClassesQueryModel[]>(this.enrollmentUrl + '/getClassesForCourse', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesByCourseCodeFromFile();
        })
      );
  }

  enrollToClass(enrollToClassCommand: EnrollToClassCommand) {
    // TODO WB: integration with backend
    // post
    // url/signForClass
  }

  private loadStudentCoursesFromFile(): Observable<StudentCoursesQueryModel[]> {
    return this.http.get<StudentCoursesQueryModel[]>('/assets/data/student-courses.json')
  }

  private loadClassesByCourseCodeFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes-by-course-code.json')
  }
}
