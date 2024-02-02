import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {CommonModule} from "@angular/common";
import {MatDialog} from "@angular/material/dialog";
import {LoginDialogComponent} from "../dialog/login-dialog.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  isUserLoggedIn: boolean = false;


  constructor(public dialog: MatDialog) {
  }

  login() {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '400px',
      height: 'auto',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
