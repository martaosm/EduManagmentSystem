import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {LecturerQueryModel} from "../interface/lecturer-query-model";
import {AssignLecturerToClassCommand} from "../classes-table/command/assign-lecturer-to-class.command";
import {
  IncreaseLimitOfStudentsInGroupCommand
} from "../classes-table/command/increase-limit-of-students-in-group.command";

@Injectable({
  providedIn: 'root',
})
export class ClassesRestService {

  constructor(private http: HttpClient) {
  }

  classesUrl = 'http://localhost:8080/classes' // TODO WB: Adjust integration with backend

  getClasses(studyPlanId: string, semester: number): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studyPlanCode", studyPlanId);
    queryParams = queryParams.append("semesterNumber", semester);
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

  increaseLimitOfStudentsInGroup(increaseLimitOfStudentsInGroupCommand: IncreaseLimitOfStudentsInGroupCommand) {
    console.log(increaseLimitOfStudentsInGroupCommand);
    // TODO WB: send update to backend
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }

  private loadLecturersFromFile(): Observable<LecturerQueryModel[]> {
    return this.http.get<LecturerQueryModel[]>('/assets/data/lecturers.json')
  }
}
