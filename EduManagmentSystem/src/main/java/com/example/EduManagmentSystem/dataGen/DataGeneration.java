package com.example.EduManagmentSystem.dataGen;

import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataGeneration {

    @Autowired
    PersonalDataRepository personalDataRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StudyMajorRepository studyMajorRepository;

    @Autowired
    StudyPlanRepository studyPlanRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    MandatoryBlockRepository mandatoryBlockRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMandBlockAssignRepository courseMandBlockAssignRepository;

    @Autowired
    ClassGroupRepository classGroupRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassPlanRepository classPlanRepository;

    @Autowired
    ClassGroupStudentAssignRepository classGroupStudentAssignRepository;

    @Autowired
    ClassDateTimeRepository classDateTimeRepository;

    @Autowired
    StudentStudyMajorAssignRepository studentStudyMajorAssignRepository;

    @EventListener
    public void writeDataToDatabase(ApplicationReadyEvent event){
//        Faker faker = new Faker();
//        List<ClassGroup> classGroups = classGroupRepository.findAll();
//        int x = 11;
//        for(ClassGroup cg : classGroups){
//            ClassDateTime classDateTime1 = new ClassDateTime();
//            classDateTime1.setDate(LocalDate.of(2024, Month.MARCH, x));
//            classDateTime1.setStartTime("16:00");
//            classDateTime1.setDurationTimeInMin(90);
//            classDateTime1.setGroupCode(cg.getGroupCode());
//            classDateTimeRepository.save(classDateTime1);
//
//            ClassDateTime classDateTime2 = new ClassDateTime();
//            classDateTime2.setDate(LocalDate.of(2024, Month.MARCH, x));
//            classDateTime2.setStartTime("16:45");
//            classDateTime2.setDurationTimeInMin(90);
//            classDateTime2.setGroupCode(cg.getGroupCode());
//            classDateTimeRepository.save(classDateTime2);
//            x++;
//        }
//
//        StudyMajor major = studyMajorRepository.findAll().get(0);
//        List<Student> students = studentRepository.findAll();
//        for(Student s : students){
//            StudentStudyMajorAssign assign = new StudentStudyMajorAssign();
//            assign.setStudentIndex(s.getIndexNumber());
//            assign.setStudyMajorCode(major.getMajorCode());
//            assign.setSemesterNumber(2);
//            studentStudyMajorAssignRepository.save(assign);
//        }
//
//        //DANE OSOBISTE
//        for(int i=0;i<10;i++){
//            PersonalData personalData = new PersonalData();
//            personalData.setName(faker.name().firstName());
//            personalData.setSurname(faker.name().lastName());
//            personalData.setEmail(personalData.getName().toLowerCase().concat(personalData.getSurname().toLowerCase()).concat("123@uczelnia.edu.pl"));
//            personalData.setPhoneNumber(faker.phoneNumber().cellPhone());
//            personalDataRepository.save(personalData);
//        }
//
//        List<PersonalData> personalData = personalDataRepository.findAll();
//
//        //KONTA
//
//        for(int i=0;i<6;i++){
//            Account account = new Account();
//            account.setUsername(personalData.get(i).getName().toLowerCase().concat("123"));
//            account.setPassword(faker.cat().breed().toLowerCase().concat("1503"));
//            account.setAccountTypeId(4L);
//            account.setPersonalDataId(personalData.get(i).getId());
//            accountRepository.save(account);
//        }
//
//        for(int i=6;i<9;i++){
//            Account account = new Account();
//            account.setUsername(personalData.get(i).getName().toLowerCase().concat("123"));
//            account.setPassword(faker.cat().breed().toLowerCase().concat("1503"));
//            account.setAccountTypeId(3L);
//            account.setPersonalDataId(personalData.get(i).getId());
//            accountRepository.save(account);
//        }
//
//        Account account = new Account();
//        account.setUsername(personalData.get(9).getName().toLowerCase().concat("123"));
//        account.setPassword(faker.cat().breed().toLowerCase().concat("1503"));
//        account.setAccountTypeId(1L);
//        account.setPersonalDataId(personalData.get(9).getId());
//        accountRepository.save(account);
//
//        //KIERUNKI
//        StudyMajor studyMajor = new StudyMajor();
//        studyMajor.setName("Informatyka Stosowana");
//        studyMajorRepository.save(studyMajor);
//
//        //PLAN STUDIOW
//        StudyPlan studyPlan = new StudyPlan();
//        studyPlan.setDepartment("W4n");
//        studyPlan.setEducationLevel("magisterskie");
//        studyPlan.setStudyForm("stacjonarne");
//        studyPlan.setStudyProfile("ogólnoakademickie");
//        studyPlan.setSpecialization("Inżyniera oprogramowania");
//        studyPlan.setStudyLanguage("polski");
//        studyPlan.setInMotionSinceEduCycle("2022-2023");
//        studyPlan.setPlanStatusId(1L);
//        studyPlan.setMajorCode(studyMajorRepository.findAll().get(0).getMajorCode());
//        studyPlanRepository.save(studyPlan);
//
//        //SEMESTER
//        Semester semester3 = new Semester();
//        semester3.setSemesterNumber(3);
//        semester3.setStudyPlanCode(studyPlanRepository.findAll().get(0).getStudyPlanCode());
//        semesterRepository.save(semester3);
//        Semester semester2 = new Semester();
//        semester2.setSemesterNumber(2);
//        semester2.setStudyPlanCode(studyPlanRepository.findAll().get(0).getStudyPlanCode());
//        semesterRepository.save(semester2);
//
//        //BLOK OBOWIAZKOWY
//        MandatoryBlock mandatoryBlock1 = new MandatoryBlock();
//        mandatoryBlock1.setName("Blok obowiazkowy");
//        mandatoryBlock1.setSemesterId(semesterRepository.findAll().get(0).getId());
//        mandatoryBlockRepository.save(mandatoryBlock1);
//        MandatoryBlock mandatoryBlock2 = new MandatoryBlock();
//        mandatoryBlock2.setName("Blok obowiazkowy");
//        mandatoryBlock2.setSemesterId(semesterRepository.findAll().get(1).getId());
//        mandatoryBlockRepository.save(mandatoryBlock2);
//
//        mandatoryBlock1 = mandatoryBlockRepository.findAll().get(0);
//        mandatoryBlock2 = mandatoryBlockRepository.findAll().get(1);
//
//        //KURSY SEMESTR 3
//        Course course1 = new Course();
//        course1.setNameInPolish("Seminarium dyplomowe");
//        course1.setNumberOfHoursZZU(30);
//        course1.setNumberOfHoursCNPS(60);
//        course1.setNumberOfPointsECTS(2);
//        course1.setClassTypeId(5L);
//        courseRepository.save(course1);
//
//        Course course2 = new Course();
//        course2.setNameInPolish("Przetwarzanie dużych zbiorów danych");
//        course2.setNumberOfHoursZZU(30);
//        course2.setNumberOfHoursCNPS(90);
//        course2.setNumberOfPointsECTS(3);
//        course2.setClassTypeId(4L);
//        courseRepository.save(course2);
//
//        Course course3 = new Course();
//        course3.setNameInPolish("Przetwarzanie dużych zbiorów danych");
//        course3.setNumberOfHoursZZU(30);
//        course3.setNumberOfHoursCNPS(60);
//        course3.setNumberOfPointsECTS(2);
//        course3.setClassTypeId(1L);
//        courseRepository.save(course3);
//
//        Course course4 = new Course();
//        course4.setNameInPolish("Systemy wyszukiwania informacji");
//        course4.setNumberOfHoursZZU(30);
//        course4.setNumberOfHoursCNPS(60);
//        course4.setNumberOfPointsECTS(2);
//        course4.setClassTypeId(4L);
//        courseRepository.save(course4);
//
//        Course course5 = new Course();
//        course5.setNameInPolish("Systemy wyszukiwania informacji");
//        course5.setNumberOfHoursZZU(15);
//        course5.setNumberOfHoursCNPS(30);
//        course5.setNumberOfPointsECTS(1);
//        course5.setClassTypeId(1L);
//        courseRepository.save(course5);
//
//        Course course6 = new Course();
//        course6.setNameInPolish("Etyka nowych technologii");
//        course6.setNumberOfHoursZZU(15);
//        course6.setNumberOfHoursCNPS(60);
//        course6.setNumberOfPointsECTS(2);
//        course6.setClassTypeId(5L);
//        courseRepository.save(course6);
//
//        // KURSY SEMESTR 2
//        Course course7 = new Course();
//        course7.setNameInPolish("Projektowanie systemów informatycznych");
//        course7.setNumberOfHoursZZU(30);
//        course7.setNumberOfHoursCNPS(90);
//        course7.setNumberOfPointsECTS(3);
//        course7.setClassTypeId(4L);
//        courseRepository.save(course7);
//
//        Course course8 = new Course();
//        course8.setNameInPolish("Projektowanie systemów informatycznych");
//        course8.setNumberOfHoursZZU(15);
//        course8.setNumberOfHoursCNPS(60);
//        course8.setNumberOfPointsECTS(2);
//        course8.setClassTypeId(1L);
//        courseRepository.save(course8);
//
//        Course course9 = new Course();
//        course9.setNameInPolish("Podstawy biznesu i ochrona własności intelektualnej");
//        course9.setNumberOfHoursZZU(30);
//        course9.setNumberOfHoursCNPS(90);
//        course9.setNumberOfPointsECTS(3);
//        course9.setClassTypeId(1L);
//        courseRepository.save(course9);
//
//        Course course10 = new Course();
//        course10.setNameInPolish("Bezpieczeństwo systemów webowych i mobilnych");
//        course10.setNumberOfHoursZZU(15);
//        course10.setNumberOfHoursCNPS(60);
//        course10.setNumberOfPointsECTS(2);
//        course10.setClassTypeId(3L);
//        courseRepository.save(course10);
//
//        Course course11 = new Course();
//        course11.setNameInPolish("Bezpieczeństwo systemów webowych i mobilnych");
//        course11.setNumberOfHoursZZU(15);
//        course11.setNumberOfHoursCNPS(30);
//        course11.setNumberOfPointsECTS(1);
//        course11.setClassTypeId(5L);
//        courseRepository.save(course11);
//
//        Course course12 = new Course();
//        course12.setNameInPolish("Zaawansowane systemy baz danych");
//        course12.setNumberOfHoursZZU(30);
//        course12.setNumberOfHoursCNPS(60);
//        course12.setNumberOfPointsECTS(2);
//        course12.setClassTypeId(4L);
//        courseRepository.save(course12);
//
//        //PRZYPISANIE KURSU DO BLOKU
//        List<Course> courses = courseRepository.findAll();
//        for(int i=0; i<6; i++){
//            CourseMandBlockAssign mandBlockAssign = new CourseMandBlockAssign();
//            mandBlockAssign.setMandBlockId(mandatoryBlock1.getId());
//            mandBlockAssign.setCourseCode(courses.get(i).getCourseCode());
//            courseMandBlockAssignRepository.save(mandBlockAssign);
//        }
//
//        //PRZYPISANIE KURSU DO BLOKU
//        for(int i=6; i<12; i++){
//            CourseMandBlockAssign mandBlockAssign = new CourseMandBlockAssign();
//            mandBlockAssign.setMandBlockId(mandatoryBlock2.getId());
//            mandBlockAssign.setCourseCode(courses.get(i).getCourseCode());
//            courseMandBlockAssignRepository.save(mandBlockAssign);
//        }
//
//        //GRUPY ZAJECIOWE
//        for(Course c : courses){
//            ClassGroup classGroup = new ClassGroup();
//            classGroup.setPlaceLimit(15);
//            classGroup.setRegisteredStudents(13);
//            classGroup.setClassTypeId(c.getClassTypeId());
//            classGroup.setCourseCode(c.getCourseCode());
//            classGroupRepository.save(classGroup);
//        }
//
//        //STUDENCI
//        List<Account> accounts = accountRepository.findAll();
//        for(Account a : accounts){
//            if(a.getAccountTypeId().equals(4L)){
//                Student student = new Student();
//                student.setStudyStartDate(LocalDate.parse("2019-09-01"));
//                student.setAccountId(a.getId());
//                studentRepository.save(student);
//            }else if(a.getAccountTypeId().equals(3L)){
//                Teacher teacher = new Teacher();
//                teacher.setTeacherPositionId(1L);
//                teacher.setTitle("Profesor");
//                teacher.setAcademicDegree("Profesor");
//                teacher.setAccountId(a.getId());
//                teacherRepository.save(teacher);
//            }
//        }
//
//        //ROZKLAD ZAJEC
//        List<ClassGroup> classGroups = classGroupRepository.findAll();
//        for(ClassGroup cg : classGroups){
//            ClassPlan classPlan = new ClassPlan();
//            classPlan.setEducationCycle("2022/2023");
//            classPlan.setMajorCode(studyMajorRepository.findAll().get(0).getMajorCode());
//            classPlan.setClassGroupCode(cg.getGroupCode());
//            classPlanRepository.save(classPlan);
//        }
//
//        // PRZYPISANIE STUDENTOW DO GRUP
//        List<Student> students = studentRepository.findAll();
//        for(Student s : students){
//            for(ClassGroup cg : classGroups){
//                ClassGroupStudentAssign cgsa = new ClassGroupStudentAssign();
//                cgsa.setGroupCode(cg.getGroupCode());
//                cgsa.setStudentIndex(s.getIndexNumber());
//                classGroupStudentAssignRepository.save(cgsa);
//            }
//        }
    }
}
