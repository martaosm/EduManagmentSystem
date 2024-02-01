import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {CoursesQueryModel} from "../interface/courses-query-model";

@Component({
  selector: 'courses-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './courses-table.component.html',
  styleUrl: './courses-table.component.scss'
})
export class CoursesTableComponent {

  @Input()
  courses!: CoursesQueryModel[];

  params: any;

  colDefs: ColDef[] = [
    {field: "nameInPolish", headerName: "Nazwa kursu"},
    {field: "courseCode", headerName: "Kod kursu"},
    {field: "numberOfPointsECTS", headerName: "Liczba punktów ECTS"},
    {field: "classType", headerName: "Forma zajęć"},
    {field: "numberOfHoursZZU", headerName: "Liczba godzin ZZU"},
    {field: "numberOfHoursCNPS", headerName: "Liczba godzin CNPS"},
  ];

  onGridReady(params: any): void {
    this.params=params;
    params.api.sizeColumnsToFit();
  }
}
