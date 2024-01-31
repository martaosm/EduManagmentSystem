import {Component} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatDialogRef} from "@angular/material/dialog";
import {PredefinedGradeValues} from "../../../../../shared/enum/predefined-grade-values";

@Component({
  selector: 'add-grade-dialog',
  templateUrl: 'add-grade-dialog.component.html',
  styleUrl: 'add-grade-dialog.component.scss',
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
export class AddGradeDialogComponent {

  selectedOption: PredefinedGradeValues | undefined;

  grades: PredefinedGradeValues[] = [
    PredefinedGradeValues.GRADE_2,
    PredefinedGradeValues.GRADE_3,
    PredefinedGradeValues.GRADE_3_5,
    PredefinedGradeValues.GRADE_4,
    PredefinedGradeValues.GRADE_4_5,
    PredefinedGradeValues.GRADE_5,
    PredefinedGradeValues.GRADE_5_5,
  ];

  constructor(public dialogRef: MatDialogRef<AddGradeDialogComponent>) {
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.selectedOption);
  }
}
