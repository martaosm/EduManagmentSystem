import {Component, Input} from '@angular/core';
import {AgGridAngular} from 'ag-grid-angular';
import {ColDef} from 'ag-grid-community';
import {StudyPlanQueryModel} from "../interface/study-plan-query-model";
import {
  ActionButtonCellRendererComponent
} from "./cell-renderers/action-button-cell-renderer/action-button-cell-renderer.component";

@Component({
  selector: 'study-plan-table',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './study-plan-table.component.html',
  styleUrl: './study-plan-table.component.scss'
})
export class StudyPlanTableComponent {

  @Input()
  studyPlans!: StudyPlanQueryModel[];

  colDefs: ColDef[] = [
    {field: "inMotionSinceEduCycle", headerName: "Rok Akademicki"},
    {field: "planStatus", headerName: "Status"},
    {field: "majorName", headerName: "Kierunek Studiów", tooltipValueGetter: params => params.value},
    {field: "majorCode", headerName: "Kod Kierunku"},
    {field: "specialization", headerName: "Specjalizacja", tooltipValueGetter: params => params.value},
    {field: "faculty", headerName: "Wydział", tooltipValueGetter: params => params.value},
    {field: "educationLevel", headerName: "Poziom Kształcenia"},
    {field: "studyMode", headerName: "Forma Studiów"},
    {
      field: "actions", headerName: "",
      cellRenderer: ActionButtonCellRendererComponent,
    }
  ];

  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }
}
