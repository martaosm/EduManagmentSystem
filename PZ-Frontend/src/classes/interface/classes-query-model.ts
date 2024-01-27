import {LecturerQueryModel} from "./lecturer-query-model";

export interface ClassesQueryModel {
  courseName: string,
  classDate: string,
  classType: string,
  classId: string,
  lecturer: LecturerQueryModel | null,
  numberOfPlacesTaken: number,
  numberOfPlacesOverall: number
}
