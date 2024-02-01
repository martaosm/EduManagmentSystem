import {LecturerQueryModel} from "./lecturer-query-model";

export interface ClassesQueryModel {
  groupCode: string,
  courseName: string,
  classType: string,
  teacherResponse: LecturerQueryModel | null,
  courseCode: string,
  classDate: string,
  registeredStudents: number,
  placeLimit: number
}
