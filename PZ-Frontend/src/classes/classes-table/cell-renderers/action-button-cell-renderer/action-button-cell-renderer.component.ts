import {Component, inject} from "@angular/core";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {MatIcon} from "@angular/material/icon";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {MatDialog} from "@angular/material/dialog";
import {CommonModule} from "@angular/common";
import {ClassesRestService} from "../../../rest/classes-rest.service";
import {LecturerQueryModel} from "../../../interface/lecturer-query-model";
import {AddLecturerDialogComponent} from "../../dialog/add-lecturer-dialog/add-lecturer-dialog.component";
import {AssignLecturerToClassCommand} from "../../command/assign-lecturer-to-class.command";
import {
  IncreaseLimitOfStudentsInGroupDialogComponent
} from "../../dialog/increase-limit-of-students-in-group-dialog/increase-limit-of-students-in-group-dialog.component";
import {IncreaseLimitOfStudentsInGroupCommand} from "../../command/increase-limit-of-students-in-group.command";

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

  lecturers: LecturerQueryModel[] = [];

  private dialog: MatDialog = inject(MatDialog);
  private classesRestService: ClassesRestService = inject(ClassesRestService);

  private params: any;

  agInit(params: any): void {
    this.params = params;
    this.shouldAddLecturerButtonBeActive = params.data.teacherResponse.id;
    this.lecturers = params.lecturers;
  }

  refresh(params: any): boolean {
    return false;
  }

  onAddLecturerClicked($event: MouseEvent): void {
    this.classesRestService.getLecturers().subscribe((lecturers: LecturerQueryModel[]) => {
      const dialogRef = this.dialog.open(AddLecturerDialogComponent, {
        width: '500px',
        height: 'auto',
        data: {
          selectedOption: '',
          options: lecturers,
        },
      });

      dialogRef.afterClosed().subscribe(selectedLecturer => {
        if (selectedLecturer) {
          const assignLecturerToClassCommand = {
            lecturerId: selectedLecturer.id,
            classId: this.params.data.groupCode
          } as AssignLecturerToClassCommand;
          this.classesRestService.assignLecturerToClass(assignLecturerToClassCommand)
        }
      });
    });
  }

  onIncreaseLimitOfStudentsInGroupClicked($event: MouseEvent): void {
    const dialogRef = this.dialog.open(IncreaseLimitOfStudentsInGroupDialogComponent, {
      width: '500px',
      height: 'auto',
      data: {
        initialValue: this.params.data.placeLimit
      }
    });

    dialogRef.afterClosed().subscribe(newGroupOccupancyLimit => {
      const increaseLimitOfStudentsInGroupCommand = {
        newClassPlaceLimit: newGroupOccupancyLimit,
        classId: this.params.data.groupCode
      } as IncreaseLimitOfStudentsInGroupCommand;
      this.classesRestService.increaseLimitOfStudentsInGroup(increaseLimitOfStudentsInGroupCommand)

    });
  }
}
