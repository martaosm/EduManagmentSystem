import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ClassesQueryModel} from "./interface/classes-query-model";
import {ClassesTableComponent} from "./classes-table/classes-table.component";
import {ClassesBannerComponent} from "./banner/classes-banner.component";

@Component({
  selector: 'classes',
  standalone: true,
  imports: [
    ClassesTableComponent,
    ClassesBannerComponent
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
