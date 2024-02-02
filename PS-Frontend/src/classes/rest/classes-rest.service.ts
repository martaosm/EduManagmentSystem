import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {ClassesQueryModel} from "../interface/classes-query-model";

@Injectable({
  providedIn: 'root',
})
export class ClassesRestService {

  constructor(private http: HttpClient) {
  }

  classesUrl = 'http://a14165f188422408a841c9e6723554ac-884258971.us-east-1.elb.amazonaws.com:8080'

  getClasses(studyPlanId: string, semester: number): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studyPlanCode", studyPlanId);
    queryParams = queryParams.append("semesterNumber", semester);
    return this.http.get<ClassesQueryModel[]>(this.classesUrl + '/getTimetableForStudyPlan', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesFromFile();
        })
      );
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }
}
