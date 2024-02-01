import {GradesQueryModel} from "./grades-query-model";

export interface SemesterGradesQueryModel {
  semesterNumber: number,
  grades: GradesQueryModel[],
}
