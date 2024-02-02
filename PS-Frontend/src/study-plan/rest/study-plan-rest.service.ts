import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StudyPlanQueryModel} from "../interface/study-plan-query-model";
import {catchError, Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class StudyPlanRestService {

  constructor(private http: HttpClient) {
  }

  studyPlanUrl = 'http://ac9ab1e1a723b4f60930fb644b3ab8aa-90188893.us-east-1.elb.amazonaws.com:8080'

  getStudyPlans(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>(this.studyPlanUrl + '/getAllStudyPlans')
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudyPlansFromFile();
        })
      );
  }

  private loadStudyPlansFromFile(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>('/assets/data/studyPlans.json')
  }
}
