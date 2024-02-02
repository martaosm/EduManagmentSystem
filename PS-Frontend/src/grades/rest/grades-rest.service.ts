import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable} from "rxjs";
import {SemesterGradesQueryModel} from "../interface/semester-grades-query-model";

@Injectable({
  providedIn: 'root',
})
export class GradesRestService {

  gradesUrl = 'index-service.backend.svc.cluster.local:8080'


  constructor(private http: HttpClient) {
  }

  getGradesForStudent(studentIndex: string): Observable<SemesterGradesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studentIndex", studentIndex);
    return this.http.get<SemesterGradesQueryModel[]>(this.gradesUrl + '/allStudentGrades', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadGradesFromFile();
        })
      );
  }

  private loadGradesFromFile(): Observable<SemesterGradesQueryModel[]> {
    return this.http.get<SemesterGradesQueryModel[]>('/assets/data/grades.json')
  }
}