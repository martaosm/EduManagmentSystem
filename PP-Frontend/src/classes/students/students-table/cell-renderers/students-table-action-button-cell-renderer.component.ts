import {ICellRendererAngularComp} from "ag-grid-angular";
import {Component, inject} from "@angular/core";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {MatDialog} from "@angular/material/dialog";
import {AddGradeDialogComponent} from "../dialog/add-grade-dialog/add-grade-dialog.component";
import {ClassesRestService} from "../../../rest/classes-rest.service";
import {PredefinedGradeValues} from "../../../../shared/enum/predefined-grade-values";
import {AuthenticationService} from "../../../../app/authentication.service";
import {AddGradeCommand} from "../../../classes-table/command/add-grade.command";

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

  private dialog: MatDialog = inject(MatDialog);
  private classesRestService: ClassesRestService = inject(ClassesRestService);
  private authenticationService: AuthenticationService = inject(AuthenticationService);

  agInit(params: any): void {
    this.params = params;
    this.shouldAddGradeButtonBeActive = params.data.grade;
  }

  refresh(params: any): boolean {
    return false;
  }

  onAddGradeClicked($event: MouseEvent) {
    const dialogRef = this.dialog.open(AddGradeDialogComponent, {
      width: '500px',
      height: 'auto',
    });

    dialogRef.afterClosed().subscribe((selectedGrade: PredefinedGradeValues) => {
      if (selectedGrade) {
        const addGradeCommand = {
          lecturerId: this.authenticationService.authenticatedLecturerId,
          studentId: this.params.data.studentId,
          grade: selectedGrade
        } as AddGradeCommand;
        this.classesRestService.addGrade(addGradeCommand);
      }
    });
  }
}
