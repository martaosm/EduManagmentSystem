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
    {field: "courseName", headerName: "Nazwa kursu"},
    {field: "numberOfEctsPoints", headerName: "Liczba punktów ECTS"},
    {field: "courseType", headerName: "Forma zajęć"},
    {field: "numberOfZZUHours", headerName: "Liczba godzin ZZU"},
    {field: "numberOfCNPSHours", headerName: "Liczba godzin CNPS"},
  ];

  onGridReady(params: any): void {
    this.params=params;
    params.api.sizeColumnsToFit();
  }
}
