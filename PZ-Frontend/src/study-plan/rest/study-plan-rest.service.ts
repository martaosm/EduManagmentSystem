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

  studyPlanUrl = 'http://localhost:8080/study-plans' // TODO WB: Adjust integration with backend

  getStudyPlans(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>(this.studyPlanUrl)
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudyPlansFromFile();
        })
      );
  }

  archivePlan(studyPlanId: string) {
    // TODO WB: Send http request to backend to archive the plan
  }

  private loadStudyPlansFromFile(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>('/assets/data/studyPlans.json')
  }
}
