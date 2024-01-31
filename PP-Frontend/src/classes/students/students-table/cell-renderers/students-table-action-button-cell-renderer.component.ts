import {ICellRendererAngularComp} from "ag-grid-angular";
import {Component} from "@angular/core";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'students-table-action-button-cell-renderer',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatIconButton,
    MatTooltip
  ],
  templateUrl: './students-table-action-button-cell-renderer.component.html',
  styleUrl: './students-table-action-button-cell-renderer.component.scss'
})
export class StudentsTableActionButtonCellRendererComponent implements ICellRendererAngularComp {

  private params: any;

  shouldAddGradeButtonBeActive: boolean = false;

  agInit(params: any): void {
    this.params = params;
    this.shouldAddGradeButtonBeActive = params.data.grade;
  }

  refresh(params: any): boolean {
    return false;
  }

  onAddGradeClicked($event: MouseEvent) {
    // TODO WB: add grade
  }
}
