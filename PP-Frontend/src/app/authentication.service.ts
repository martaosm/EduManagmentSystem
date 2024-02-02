import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticatedLecturerId!: string;

  setAuthenticatedLecturerId(lecturerId: string) {
    this.authenticatedLecturerId=lecturerId;
  }
}
