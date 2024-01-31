import {PredefinedGradeValues} from "../../../shared/enum/predefined-grade-values";

export interface AddGradeCommand {
  lecturerId: string,
  studentId: string,
  grade: PredefinedGradeValues
}
