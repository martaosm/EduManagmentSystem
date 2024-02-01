import {CoursesQueryModel} from "./courses-query-model";

export interface SemesterCoursesQueryModel {
  semesterNumber: number,
  courses: CoursesQueryModel[]
}
