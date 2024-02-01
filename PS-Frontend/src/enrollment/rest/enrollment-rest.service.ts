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

  enrollmentUrl = 'http://localhost:8080/enrollment' // TODO WB: Adjust integration with backend

  constructor(private http: HttpClient) {
  }

  getStudentCourses(studentId: string): Observable<StudentCoursesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studentId", studentId);
    return this.http.get<StudentCoursesQueryModel[]>(this.enrollmentUrl + '/courses', {params: queryParams})
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
    return this.http.get<ClassesQueryModel[]>(this.enrollmentUrl + '/classes', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesByCourseCodeFromFile();
        })
      );
  }

  enrollToClass(enrollToClassCommand: EnrollToClassCommand) {
    // TODO WB: integration with backend
  }

  private loadStudentCoursesFromFile(): Observable<StudentCoursesQueryModel[]> {
    return this.http.get<StudentCoursesQueryModel[]>('/assets/data/student-courses.json')
  }

  private loadClassesByCourseCodeFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes-by-course-code.json')
  }
}
