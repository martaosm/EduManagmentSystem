import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {LecturerQueryModel} from "../interface/lecturer-query-model";
import {AssignLecturerToClassCommand} from "../classes-table/comand/assign-lecturer-to-class.command";

@Injectable({
  providedIn: 'root',
})
export class ClassesRestService {

  constructor(private http: HttpClient) {
  }

  classesUrl = 'http://localhost:8080/classes' // TODO WB: Adjust integration with backend

  getClasses(studyPlanId: number): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studyPlanId", studyPlanId);
    return this.http.get<ClassesQueryModel[]>(this.classesUrl, {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesFromFile();
        })
      );
  }

  getLecturers(): Observable<LecturerQueryModel[]> {
    return this.http.get<LecturerQueryModel[]>(this.classesUrl + '/lecturers')
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadLecturersFromFile();
        })
      );
  }

  assignLecturerToClass(assignLecturerToClassCommand: AssignLecturerToClassCommand) {
    console.log(assignLecturerToClassCommand);
    // TODO WB: send update to backend
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }

  private loadLecturersFromFile(): Observable<LecturerQueryModel[]> {
    return this.http.get<LecturerQueryModel[]>('/assets/data/lecturers.json')
  }
}
