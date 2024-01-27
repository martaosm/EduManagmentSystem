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
export class StudyPlanTableComponent {

  @Input()
  classes!: ClassesQueryModel[];

  colDefs: ColDef[] = [
    {field: "courseName", headerName: "Nazwa kursu", tooltipValueGetter: params => params.value},
    {field: "classDate", headerName: "Termin zajęć"},
    {field: "classType", headerName: "Typ zajęć"},
    {field: "classId", headerName: "Kod grupy zajęciowej"},
    {field: "lecturer", headerName: "Prowadzący", valueFormatter: (params: any) => LecturerUtils.formatLecturer(params.data.lecturer)},
    {field: "groupOccupancy", headerName: "Zajętość grupy", valueFormatter: (params: any) => ClassesUtil.formatGroupOccupancy(params.data.numberOfPlacesTaken, params.data.numberOfPlacesOverall)},
    {
      field: "actions", headerName: "", width: 100,
      cellRenderer: ActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
