import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {StudentQueryModel} from "../interface/student-query-model";
import {AddGradeCommand} from "../classes-table/command/add-grade.command";

@Injectable({
  providedIn: 'root',
})
export class ClassesRestService {

  constructor(private http: HttpClient) {
  }

  classesUrl = 'http://localhost:8080/classes' // TODO WB: Adjust integration with backend

  getClassesForLecturer(lecturerId: string): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("lecturerId", lecturerId);
    return this.http.get<ClassesQueryModel[]>(this.classesUrl, {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesFromFile();
        })
      );
  }

  getStudentsForClass(groupCode: string): Observable<StudentQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("groupCode", groupCode);
    return this.http.get<StudentQueryModel[]>(this.classesUrl,{params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudentsFromFile();
        })
      );
  }

  addGrade(addGradeCommand: AddGradeCommand) {
    //TODO WB: Adjust integration with backend
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }

  private loadStudentsFromFile(): Observable<StudentQueryModel[]> {
    return this.http.get<StudentQueryModel[]>('/assets/data/students.json')
  }
}
