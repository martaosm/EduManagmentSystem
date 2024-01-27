import {Component, inject} from "@angular/core";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'action-button-cell-renderer',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatIconButton,
    MatTooltip,
    CommonModule
  ],
  templateUrl: './action-button-cell-renderer.component.html',
  styleUrl: './action-button-cell-renderer.component.scss'
})
export class ActionButtonCellRendererComponent implements ICellRendererAngularComp {

  shouldAddLecturerButtonBeActive: boolean = false;

  private dialog = inject(MatDialog);

  private router = inject(Router)
  private params: any;

  agInit(params: any): void {
    this.params = params;
    this.shouldAddLecturerButtonBeActive=params.data.lecturer;
  }

  refresh(params: any): boolean {
    return false;
  }

  onAddLecturerClicked($event: MouseEvent) {
  }
}
