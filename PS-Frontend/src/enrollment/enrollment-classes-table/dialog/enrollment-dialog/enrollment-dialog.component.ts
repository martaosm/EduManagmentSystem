import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {EnrollmentUtils} from "../../../../shared/util/enrollment.utils";
import {ClassesQueryModel} from "../../../../classes/interface/classes-query-model";

@Component({
  selector: 'enrollment-dialog',
  templateUrl: 'enrollment-dialog.component.html',
  styleUrl: 'enrollment-dialog.component.scss',
  standalone: true,
  imports: [
    MatFormField,
    MatSelect,
    MatOption,
    MatButton,
    MatLabel,
    FormsModule,
    CommonModule,
  ]
})
export class EnrollmentDialogComponent {

  data = inject(MAT_DIALOG_DATA);

  constructor(public dialogRef: MatDialogRef<EnrollmentDialogComponent>) {
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.data.selectedOption);
  }

  formatValue(classesQueryModel: ClassesQueryModel): string {
    return EnrollmentUtils.formatClass(classesQueryModel);
  }

  shouldDisableClassOption(classesQueryModel: ClassesQueryModel): boolean {
    return classesQueryModel.registeredStudents === classesQueryModel.placeLimit;
  }
}
