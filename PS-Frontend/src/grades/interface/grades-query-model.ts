import {PredefinedGradeValues} from "../../shared/enum/predefined-grade-values";

export interface GradesQueryModel {
  gradeValue: PredefinedGradeValues,
  courseName: string,
  classType: string
}
