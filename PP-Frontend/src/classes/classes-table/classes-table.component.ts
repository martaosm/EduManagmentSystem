import {Component, Input} from "@angular/core";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {ActionButtonCellRendererComponent} from "./cell-renderers/action-button-cell-renderer.component";

@Component({
  selector: 'classes-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './classes-table.component.html',
  styleUrl: './classes-table.component.scss'
})
export class ClassesTableComponent {

  @Input()
  classes!: ClassesQueryModel[];

  colDefs: ColDef[] = [
    {field: "groupCode", headerName: "Kod grupy"},
    {field: "courseName", headerName: "Nazwa kursu"},
    {field: "classType", headerName: "Typ zajęć"},
    {field: "courseCode", headerName: "Kod kursu"},
    {field: "classDate", headerName: "Data zajęć"},
    {field: "registeredStudent", headerName: "Liczba zapisanych studentów"},
    {
      field: "actions", headerName: "", width: 100,
      cellRenderer: ActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
