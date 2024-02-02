import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {StudyPlanQueryModel} from "./interface/study-plan-query-model";
import {StudyPlanTableComponent} from "./study-plan-table/study-plan-table.component";
import {StudyPlanBannerComponent} from "./study-plan-table/banner/study-plan-banner.component";

@Component({
  selector: 'app-study-plan',
  standalone: true,
  imports: [
    StudyPlanTableComponent,
    StudyPlanBannerComponent
  ],
  templateUrl: './study-plan.component.html',
  styleUrl: './study-plan.component.scss'
})
export class StudyPlanComponent implements OnInit {

  studyPlans: StudyPlanQueryModel[] = [];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({studyPlans}) => {
      console.log(studyPlans);
      this.studyPlans = studyPlans;
    })
  }
}
