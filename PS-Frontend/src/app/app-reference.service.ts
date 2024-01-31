import { Injectable, ApplicationRef } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AppReferenceService {
  private appComponentRef: any;

  constructor(private appRef: ApplicationRef) {}

  setAppComponentRef(appComponentRef: any) {
    this.appComponentRef = appComponentRef;
  }

  getAppComponentRef() {
    return this.appComponentRef;
  }
}
