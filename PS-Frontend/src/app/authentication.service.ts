import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticatedStudentId: string = "studentId" //TODO WB: hardcoded studentId
}
