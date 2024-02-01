import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {StudentQueryModel} from "../../interface/student-query-model";
import {
  StudentsTableActionButtonCellRendererComponent
} from "./cell-renderers/students-table-action-button-cell-renderer.component";

@Component({
  selector: 'students-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './students-table.component.html',
  styleUrl: './students-table.component.scss'
})
export class StudentsTableComponent {

  @Input()
  students!: StudentQueryModel[];

  colDefs: ColDef[] = [
    {field: "index", headerName: "Indeks"},
    {field: "firstName", headerName: "Imię"},
    {field: "lastName", headerName: "Nazwisko"},
    {field: "gradeValue", headerName: "Ocena"},
    {
      field: "actions", headerName: "", width: 100,
      cellRenderer: StudentsTableActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
