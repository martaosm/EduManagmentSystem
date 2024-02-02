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

  indexServiceUrl = 'http://a64d1ac5d5a5a4c14acfddc03cbac182-1831233801.us-east-1.elb.amazonaws.com:8080'

  getClassesForLecturer(teacherId: string): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("teacherId", teacherId);
    return this.http.get<ClassesQueryModel[]>(this.indexServiceUrl + '/getAllClassesForTeacher', {params: queryParams})
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
    return this.http.get<StudentQueryModel[]>(this.indexServiceUrl + '/getAllStudentsForClassGroup',{params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudentsFromFile();
        })
      );
  }

  addGrade(addGradeCommand: AddGradeCommand) {
    return this.http.post(this.indexServiceUrl + '/addNewGrade', addGradeCommand).subscribe(
      response => {
        console.log(response)
      },
      error => {
        console.log(error)
      }
    )
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }

  private loadStudentsFromFile(): Observable<StudentQueryModel[]> {
    return this.http.get<StudentQueryModel[]>('/assets/data/students.json')
  }
}
