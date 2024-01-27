import {LecturerQueryModel} from "./lecturer-query-model";

export interface ClassesQueryModel {
  courseName: string,
  classDate: string,
  classType: string,
  clasId: string,
  lecturer: LecturerQueryModel | null
}
