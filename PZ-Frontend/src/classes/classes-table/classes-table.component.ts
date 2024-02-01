import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {
  ActionButtonCellRendererComponent
} from "./cell-renderers/action-button-cell-renderer/action-button-cell-renderer.component";
import {LecturerUtils} from "../../shared/util/lecturer.utils";
import {ClassesUtil} from "../../shared/util/classes.util";

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
    {field: "nameInPolish", headerName: "Nazwa kursu", tooltipValueGetter: params => params.value},
    {field: "classDate", headerName: "Termin zajęć"},
    {field: "classType", headerName: "Typ zajęć"},
    {field: "classId", headerName: "Kod grupy zajęciowej"},
    {field: "teacherResponse", headerName: "Prowadzący", valueFormatter: (params: any) => LecturerUtils.formatLecturer(params.data.teacherResponse)},
    {field: "groupOccupancy", headerName: "Zajętość grupy", valueFormatter: (params: any) => ClassesUtil.formatGroupOccupancy(params.data.registeredStudents, params.data.placeLimit)},
    {
      field: "actions", headerName: "", width: 100,
      cellRenderer: ActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
