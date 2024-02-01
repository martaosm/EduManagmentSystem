import {Component, Input} from "@angular/core";
import {AgGridAngular} from "ag-grid-angular";
import {ColDef} from "ag-grid-community";
import {StudentCoursesQueryModel} from "../interface/student-courses-query-model";
import {EnrollmentUtils} from "../../shared/util/enrollment.utils";
import {ActionButtonCellRendererComponent} from "./cell-renderers/action-button-cell-renderer.component";

@Component({
  selector: 'enrollment-classes-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './enrollment-classes-table.component.html',
  styleUrl: './enrollment-classes-table.component.scss'
})
export class EnrollmentClassesTableComponent {

  @Input()
  studentCourses!: StudentCoursesQueryModel[];

  params: any;

  colDefs: ColDef[] = [
    {field: "courseCode", headerName: "Kod kursu"},
    {field: "courseName", headerName: "Nazwa kursu"},
    {field: "classType", headerName: "Forma zajęć"},
    {
      field: "isStudentEnrolled",
      headerName: "Status",
      valueGetter: (params: any) => EnrollmentUtils.formatStudentEnrollmentStatus(params.data.isStudentEnrolled)
    },
    {
      field: "actions", headerName: "", width: 100,
      cellRenderer: ActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any): void {
    this.params = params;
    params.api.sizeColumnsToFit();
  }
}
