import {LecturerQueryModel} from "../../classes/interface/lecturer-query-model";

export class LecturerUtils {

  public static formatLecturer(lecturer: LecturerQueryModel): string {
    return lecturer ? `${lecturer.academicTitle} ${lecturer.firstName} ${lecturer.secondName}` : '';
  }
}
