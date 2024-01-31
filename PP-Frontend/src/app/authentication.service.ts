import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticatedLecturerId: string = "lecturerId" //TODO WB: hardcoded lecturerId
}
