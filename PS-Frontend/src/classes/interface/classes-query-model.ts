import {LecturerQueryModel} from "./lecturer-query-model";

export interface ClassesQueryModel {
  courseNameInPolish: string,
  classDate: string,
  classType: string,
  classId: string,
  lecturer: LecturerQueryModel | null,
  courseCode: string,
  registeredStudents: number,
  placeLimit: number
}
