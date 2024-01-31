import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {GradesQueryModel} from "../interface/grades-query-model";
import {ColDef} from "ag-grid-community";

@Component({
  selector: 'grades-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './grades-table.component.html',
  styleUrl: './grades-table.component.scss'
})
export class GradesTableComponent {

  @Input()
  grades!: GradesQueryModel[];

  params: any;

  colDefs: ColDef[] = [
    {field: "courseName", headerName: "Nazwa kursu"},
    {field: "classType", headerName: "Forma zajęć"},
    {field: "gradeValue", headerName: "Ocena"},
  ];

  onGridReady(params: any): void {
    this.params = params;
    params.api.sizeColumnsToFit();
  }
}
