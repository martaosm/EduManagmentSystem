import {ICellRendererAngularComp} from "ag-grid-angular";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";
import {MatButton, MatIconButton} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {Component, inject} from "@angular/core";
import {MatDialog} from "@angular/material/dialog";
import {EnrollmentRestService} from "../../rest/enrollment-rest.service";
import {ClassesQueryModel} from "../../../classes/interface/classes-query-model";
import {AuthenticationService} from "../../../app/authentication.service";
import {EnrollToClassCommand} from "../command/enroll-to-class.command";
import {EnrollmentDialogComponent} from "../dialog/enrollment-dialog/enrollment-dialog.component";

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

  shouldEnrollButtonBeActive: boolean = false;

  private dialog: MatDialog = inject(MatDialog);
  private enrollmentRestService: EnrollmentRestService = inject(EnrollmentRestService);
  private authenticationService: AuthenticationService = inject(AuthenticationService);
  private params: any;

  agInit(params: any): void {
    this.params = params;
    this.shouldEnrollButtonBeActive = params.data.isStudentEnrolled;
  }

  refresh(params: any): boolean {
    return false;
  }

  onEnrollClicked($event: MouseEvent) {
    const courseCode = this.params.data.courseCode;
    this.enrollmentRestService.getClassesByCourseCode(courseCode).subscribe((classesByCourseCode: ClassesQueryModel[]) => {
      const dialogRef = this.dialog.open(EnrollmentDialogComponent, {
        width: '1000px',
        height: 'auto',
        data: {
          selectedOption: '',
          options: classesByCourseCode,
        },
      });

      dialogRef.afterClosed().subscribe(selectedClass => {
        if (selectedClass) {
          const studentId = this.authenticationService.authenticatedStudentId;
          const enrollToClassCommand = {
            studentIndex: studentId,
            classId: selectedClass.groupCode
          } as EnrollToClassCommand;
          this.enrollmentRestService.enrollToClass(enrollToClassCommand)
        }
      });

    })
  }
}
