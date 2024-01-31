import {ICellRendererAngularComp} from "ag-grid-angular";
import {Component, inject} from "@angular/core";
import {Router} from "@angular/router";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";

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

  private params: any;

  private router = inject(Router)

  agInit(params: any): void {
    this.params = params;
  }

  refresh(params: any): boolean {
    return false;
  }

  onShowStudentsClicked($event: MouseEvent) {
    const groupCode = this.params.node.data.groupCode;
    this.router.navigate(['/classes/' + groupCode])
  }
}
