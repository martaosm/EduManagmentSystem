import {Component, inject} from '@angular/core';
import {ICellRendererAngularComp} from "ag-grid-angular";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";
import {MatDialog} from "@angular/material/dialog";
import {DialogComponent} from "../../../../shared/dialog/dialog.component";
import {DialogInterface} from "../../../../shared/dialog/dialog.interface";
import {StudyPlanRestService} from "../../../rest/study-plan-rest.service";
import {Router} from "@angular/router";

@Component({
  selector: 'action-button-cell-renderer',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatIconButton,
    MatTooltip
  ],
  templateUrl: './action-button-cell-renderer.component.html',
  styleUrl: './action-button-cell-renderer.component.scss'
})
export class ActionButtonCellRendererComponent implements ICellRendererAngularComp {

  private dialog = inject(MatDialog);
  private restService = inject(StudyPlanRestService);
  private router = inject(Router)

  private params: any;

  agInit(params: any): void {
    this.params = params;
  }

  refresh(params: any): boolean {
    return false;
  }

  onArchiveClicked($event: MouseEvent) {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: {
        title: 'UWAGA',
        content: 'Czy na pewno chcesz archiwizować wybrany plan?'
      } as DialogInterface
    });

    dialogRef.afterClosed().subscribe(
      confirmation => {
        if (confirmation) {
          const studyPlanCode = this.params.node.data.id;
          this.restService.archivePlan(studyPlanCode)
        }
      }
    );

  }

  onShowSemestersClicked($event: MouseEvent) {
    const studyPlanCode = this.params.node.data.studyPlanCode;
    this.router.navigate(['/study-plan/' + studyPlanCode])
  }
}
