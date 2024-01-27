import {Component, Inject} from "@angular/core";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatButton} from "@angular/material/button";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'increase-limit-of-students-in-group-dialog',
  templateUrl: 'increase-limit-of-students-in-group-dialog.component.html',
  styleUrl: 'increase-limit-of-students-in-group-dialog.component.scss',
  standalone: true,
  imports: [
    MatFormField,
    MatSelect,
    MatOption,
    MatButton,
    MatLabel,
    MatInput,
    FormsModule,
    CommonModule,
  ]
})
export class IncreaseLimitOfStudentsInGroupDialogComponent {

  numberValue: number;

  constructor(
    public dialogRef: MatDialogRef<IncreaseLimitOfStudentsInGroupDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.numberValue = data.initialValue;
  }

  close() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.numberValue);
  }
}
