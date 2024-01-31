import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {ClassesQueryModel} from "../interface/classes-query-model";
import {LecturerUtils} from "../../shared/util/lecturer.utils";

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
    {field: "courseNameInPolish", headerName: "Nazwa kursu", tooltipValueGetter: params => params.value},
    {field: "classDate", headerName: "Termin zajęć"},
    {field: "classType", headerName: "Typ zajęć"},
    {field: "classId", headerName: "Kod grupy zajęciowej"},
    {
      field: "lecturer",
      headerName: "Prowadzący",
      valueFormatter: (params: any) => LecturerUtils.formatLecturer(params.data.lecturer)
    },
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
