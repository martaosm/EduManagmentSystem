import {GradesQueryModel} from "./grades-query-model";

export interface SemesterGradesQueryModel {
  semester: number,
  grades: GradesQueryModel[],
}
