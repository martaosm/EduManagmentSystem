import {PredefinedGradeValues} from "../../shared/enum/predefined-grade-values";

export interface StudentQueryModel {
  index: string,
  firstName: string,
  lastName: string,
  gradeValue: PredefinedGradeValues | null,
  groupCode: string
}
