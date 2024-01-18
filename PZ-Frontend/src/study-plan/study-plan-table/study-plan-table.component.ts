import {Component, Input} from '@angular/core';
import {AgGridAngular} from 'ag-grid-angular';
import {ColDef} from 'ag-grid-community';
import {StudyPlanQueryModel} from "../study-plan-query-model";
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
    {field: "academicYear", headerName: "Rok akademicki"},
    {field: "version", headerName: "Wersja"},
    {field: "status", headerName: "Status"},
    {field: "fieldOfStudy", headerName: "Kierunek Studiów"},
    {field: "faculty", headerName: "Wydział", width: 300},
    {field: "educationLevel", headerName: "Poziom Kształcenia"},
    {field: "studyMode", headerName: "Forma Studiów"},
    {field: "semester", headerName: "Semestr", width: 170},
    {
      field: "actions", headerName: "", width: 50,
      cellRenderer: ActionButtonCellRendererComponent,
      cellRendererParams: {
        clicked: (field: any) => {
          console.log('clicked')
        }
      },
    }
  ];
}
