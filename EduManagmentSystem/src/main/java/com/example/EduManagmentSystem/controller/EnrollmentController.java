package com.example.EduManagmentSystem.controller;

public class EnrollmentController {
<<<<<<< Updated upstream
=======

    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/getAllMajorsForStudent")
    public List<StudyMajorStudentResponse> getAllMajorsForStudent(@RequestParam String studentIndex){
        return enrollmentService.getAllMajorsForStudent(studentIndex);
    }

    @GetMapping("/getCoursesForStudent")
    public List<CourseResponse> getCoursesForStudent(@RequestParam String studyPlanCode,
                                                     @RequestParam int semesterNumber) throws UnknownHostException {
        return enrollmentService.getCoursesForStudent(studyPlanCode, semesterNumber);
    }

    @GetMapping("/getClassesForCourse")
    public List<ClassGroupResponse> getClassesForCourse(@RequestParam String studyPlanCode,
                                                        @RequestParam int semesterNumber,
                                                        @RequestParam String courseCode) throws Exception {
        return enrollmentService.getClassesForCourses(studyPlanCode, semesterNumber, courseCode);
    }

    //TODO: RequestBody(studentIndex, classId)
    @PostMapping("/signForClass")
    public ClassGroupStudentAssignResponse signForClass(@RequestParam String studentIndex,
                                                        @RequestParam String groupCode) throws Exception {
        return enrollmentService.signForClass(studentIndex, groupCode);
    }

    @GetMapping("/findAllClassGroupsWithPlaceLimitReached")
    public List<ClassGroupResponse> findAllClassGroupsWithPlaceLimitReached(){
        return enrollmentService.findAllClassGroupsWithPlaceLimitReached();
    }

    //TODO: Requestbody(newGroupPlaceLimit, classId)
    @PutMapping("/increasePlaceLimit")
    public ClassGroupResponse increasePlaceLimit(@RequestParam String groupCode, @RequestParam int newPlaceLimit) throws Exception {
        return enrollmentService.increasePlaceLimit(groupCode, newPlaceLimit);
    }
>>>>>>> Stashed changes
}
