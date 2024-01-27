import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {LecturerQueryModel} from "../../../interface/lecturer-query-model";
import {LecturerUtils} from "../../../../shared/util/lecturer.utils";

@Component({
  selector: 'add-lecturer-dialog',
  templateUrl: 'add-lecturer-dialog.component.html',
  styleUrl: 'add-lecturer-dialog.component.scss',
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
export class AddLecturerDialogComponent {

  data = inject(MAT_DIALOG_DATA);

  constructor(public dialogRef: MatDialogRef<AddLecturerDialogComponent>) {
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.data.selectedOption);
  }

  formatValue(lecturer: LecturerQueryModel): string {
    return LecturerUtils.formatLecturer(lecturer);
  }
}
