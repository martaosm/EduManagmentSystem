import {Component} from '@angular/core';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {AuthenticationService} from "../authentication.service";
import {MatInputModule} from "@angular/material/input";

@Component({
  selector: 'login-dialog',
  templateUrl: 'login-dialog.component.html',
  styleUrl: 'login-dialog.component.scss',
  standalone: true,
  imports: [
    MatFormField,
    MatSelect,
    MatOption,
    MatButton,
    MatLabel,
    FormsModule,
    CommonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class LoginDialogComponent {
  username: string = '';

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>,
              private authenticationService: AuthenticationService) {
  }

  close() {
    this.dialogRef.close();
  }

  login() {
    this.authenticationService.setAuthenticatedStudentId(this.username)
    this.dialogRef.close();
  }
}
