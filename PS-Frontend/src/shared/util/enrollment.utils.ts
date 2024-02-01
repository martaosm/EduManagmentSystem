import {ClassesQueryModel} from "../../classes/interface/classes-query-model";
import {LecturerUtils} from "./lecturer.utils";

export class EnrollmentUtils {

  public static formatStudentEnrollmentStatus(enrollmentStatus: boolean): string {
    if (enrollmentStatus) {
      return 'Zapisany';
    } else {
      return '';
    }
  }

  public static formatClass(classesQueryModel: ClassesQueryModel): string {
    const lecturer = classesQueryModel.lecturer ? LecturerUtils.formatLecturer(classesQueryModel.lecturer) : 'Brak prowadzącego';
    return `${classesQueryModel.classId},\n${classesQueryModel.courseNameInPolish},\n${classesQueryModel.classDate},\n${lecturer},\n${classesQueryModel.registeredStudents}/${classesQueryModel.placeLimit}`
  }
}
