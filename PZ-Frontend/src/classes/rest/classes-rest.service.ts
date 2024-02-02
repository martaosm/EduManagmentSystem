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

  timetableServiceUrl = 'http://a14165f188422408a841c9e6723554ac-884258971.us-east-1.elb.amazonaws.com:8080';
  enrollmentServiceUrl = 'http://ab7918eb8aee2425cbe51628a44765d8-1854952267.us-east-1.elb.amazonaws.com:8080';

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
