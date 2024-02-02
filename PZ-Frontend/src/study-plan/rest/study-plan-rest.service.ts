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

  studyPlanUrl = 'http://a3cf26222ba364edeaee30998ea74d4e-24029332.us-east-1.elb.amazonaws.com:8080'

  getStudyPlans(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>(this.studyPlanUrl + '/getAllStudyPlans')
      .pipe(
        catchError(error => {
          console.error('Data not received from server', error);
          return this.loadStudyPlansFromFile();
        })
      );
  }

  archivePlan(studyPlanCode: string) {
    const params = {
      studyPlanCode: studyPlanCode,
    };
    return this.http.put(this.studyPlanUrl + '/archiveStudyPlan', null, {params}).subscribe(
      response => {
        console.log(response)
      },
      error => {
        console.log(error)
      }
    )

    // TODO WB: Send http request to backend to archive the plan //put
    // url/archiveStudyPlan
  }

  private loadStudyPlansFromFile(): Observable<StudyPlanQueryModel[]> {
    return this.http.get<StudyPlanQueryModel[]>('/assets/data/studyPlans.json')
  }
}