import {LecturerQueryModel} from "../../classes/interface/lecturer-query-model";

export class LecturerUtils {

  public static formatLecturer(lecturer: LecturerQueryModel): string {
    if (lecturer && !lecturer.academicTitle && !lecturer.firstName && !lecturer.lastName) {
      return '';
    }
    return lecturer ? `${lecturer.academicTitle} ${lecturer.firstName} ${lecturer.lastName}` : '';
  }
}
