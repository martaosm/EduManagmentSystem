import {PredefinedGradeValues} from "../../shared/enum/predefined-grade-values";

export interface StudentQueryModel {
  studentId: string,
  firstName: string,
  lastName: string,
  grade: PredefinedGradeValues | null,
}
