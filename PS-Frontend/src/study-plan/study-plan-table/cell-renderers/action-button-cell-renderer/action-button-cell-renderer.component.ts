import {Component, inject} from '@angular/core';
import {ICellRendererAngularComp} from "ag-grid-angular";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";
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

  private router = inject(Router)

  private params: any;

  agInit(params: any): void {
    this.params = params;
  }

  refresh(params: any): boolean {
    return false;
  }

  onShowSemestersClicked($event: MouseEvent) {
    const studyPlanCode = this.params.node.data.studyPlanCode;
    this.router.navigate(['/study-plans/' + studyPlanCode])
  }
}
