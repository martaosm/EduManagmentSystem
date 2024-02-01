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

  timetableServiceUrl = 'http://timetable-service.backend.svc.cluster.local:8080';
  enrollmentServiceUrl = 'http://enrollment-service.backend.svc.cluster.local:8080';

  getClasses(studyPlanId: string, semester: number): Observable<ClassesQueryModel[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("studyPlanCode", studyPlanId);
    queryParams = queryParams.append("semesterNumber", semester);
    return this.http.get<ClassesQueryModel[]>(this.timetableServiceUrl + '/getTimetableForStudyPlan', {params: queryParams})
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadClassesFromFile();
        })
      );
  }

  getLecturers(): Observable<LecturerQueryModel[]> {
    return this.http.get<LecturerQueryModel[]>(this.timetableServiceUrl + '/getAllTeachers')
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
    // post
    // url/setTeacherForClassGroup
  }

  increaseLimitOfStudentsInGroup(increaseLimitOfStudentsInGroupCommand: IncreaseLimitOfStudentsInGroupCommand) {
    console.log(increaseLimitOfStudentsInGroupCommand);
    // TODO WB: send update to backend
    // enrollmentUrl/increasePlaceLimit
  }

  private loadClassesFromFile(): Observable<ClassesQueryModel[]> {
    return this.http.get<ClassesQueryModel[]>('/assets/data/classes.json')
  }

  private loadLecturersFromFile(): Observable<LecturerQueryModel[]> {
    return this.http.get<LecturerQueryModel[]>('/assets/data/lecturers.json')
  }
}
