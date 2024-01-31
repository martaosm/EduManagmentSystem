import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {GeneralEducationCourseQueryModel} from "../../../interface/general-education-course-query-model";

@Component({
  selector: 'add-general-education-course-dialog',
  templateUrl: 'add-general-education-course-dialog.component.html',
  styleUrl: 'add-general-education-course-dialog.component.scss',
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
export class AddGeneralEducationCourseDialogComponent {

  data = inject(MAT_DIALOG_DATA);

  constructor(public dialogRef: MatDialogRef<AddGeneralEducationCourseDialogComponent>) {
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.data.selectedOption);
  }

  formatValue(option: GeneralEducationCourseQueryModel) {
    return option.courseNameInPolish + ' ' + option.courseCode
  }
}
