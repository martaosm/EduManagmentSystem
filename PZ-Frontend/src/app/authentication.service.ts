import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticatedId!: string;

  setAuthenticatedId(authenticatedId: string) {
    this.authenticatedId = authenticatedId;
  }
}
