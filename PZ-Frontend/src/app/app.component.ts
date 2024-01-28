import {Component, inject, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {NavbarComponent} from "./navbar/navbar.component";
import {BannerComponent} from "./baner/banner.component";
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {AppReferenceService} from "./app-refference.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    BannerComponent,
    CommonModule,
    HttpClientModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'PZ';

  isHomePage!: boolean;
  loaderStatus: boolean = false;
  appReferenceService: AppReferenceService = inject(AppReferenceService);

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isHomePage = event.url === '/';
      }
    });
  }

  ngOnInit(): void {
    this.appReferenceService.setAppComponentRef(this);
  }

  setLoadingStatus(status: boolean): void {
    this.loaderStatus = status;
  }
}
