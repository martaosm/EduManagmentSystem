import {inject, Injectable} from '@angular/core';
import {AppReferenceService} from "./app-reference.service";

@Injectable({
  providedIn: 'root',
})
export class LoaderService {
  appReferenceService: AppReferenceService = inject(AppReferenceService);

  showLoading(): void {
    const appComponentReference = this.appReferenceService.getAppComponentRef();
    if (appComponentReference) {
      appComponentReference.setLoadingStatus(true);
    }
  }

  hideLoading(): void {
    const appComponentReference = this.appReferenceService.getAppComponentRef();
    if (appComponentReference) {
      appComponentReference.setLoadingStatus(false);
    }
  }
}
