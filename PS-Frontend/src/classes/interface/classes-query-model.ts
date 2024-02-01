import {LecturerQueryModel} from "./lecturer-query-model";

export interface ClassesQueryModel {
  courseName: string,
  classDate: string,
  classType: string,
  groupCode: string,
  teacherResponse: LecturerQueryModel | null,
  courseCode: string,
  registeredStudents: number,
  placeLimit: number
}
