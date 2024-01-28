import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ClassesQueryModel} from "./interface/classes-query-model";
import {StudyPlanBannerComponent} from "./classes-table/banner/classes-banner.component";
import {StudyPlanTableComponent} from "./classes-table/classes-table.component";

@Component({
  selector: 'classes',
  standalone: true,
  imports: [
    StudyPlanBannerComponent,
    StudyPlanTableComponent,
  ],
  templateUrl: './classes.component.html',
  styleUrl: './classes.component.scss'
})
export class ClassesComponent {

  classes: ClassesQueryModel[] = [];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({classes}) => {
      this.classes = classes;
    })
  }
}
