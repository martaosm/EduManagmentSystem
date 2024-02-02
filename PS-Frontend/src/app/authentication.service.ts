import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticatedStudentId!: string;

  setAuthenticatedStudentId(studentId: string) {
    this.authenticatedStudentId = studentId;
  }
}
