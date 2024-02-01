import {PredefinedGradeValues} from "../../../shared/enum/predefined-grade-values";

export interface AddGradeCommand {
  teacherId: string,
  studentIndex: string,
  gradeValue: PredefinedGradeValues,
  groupCode: string
}
