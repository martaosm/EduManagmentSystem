import {CoursesQueryModel} from "./courses-query-model";

export interface SemesterCoursesQueryModel {
  semester: number,
  courses: CoursesQueryModel[]
}
