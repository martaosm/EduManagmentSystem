import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable} from "rxjs";
import {SemesterGradesQueryModel} from "../interface/semester-grades-query-model";

@Injectable({
  providedIn: 'root',
})
export class GradesRestService {

  gradesUrl = 'http://localhost:8080/grades' // TODO WB: Adjust integration with backend


  constructor(private http: HttpClient) {
  }

  getGradesForStudent(studentId: string): Observable<SemesterGradesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studentId", studentId);
    return this.http.get<SemesterGradesQueryModel[]>(this.gradesUrl, {params: queryParams})
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
