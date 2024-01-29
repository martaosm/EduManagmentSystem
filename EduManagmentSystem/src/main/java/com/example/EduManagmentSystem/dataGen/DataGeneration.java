package com.example.EduManagmentSystem.dataGen;

import com.example.EduManagmentSystem.model.*;
import com.example.EduManagmentSystem.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
public class DataGeneration {
//    @Autowired
//    PersonalDataRepository personalDataRepository;
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    StudyMajorRepository studyMajorRepository;
//
//    @Autowired
//    StudyPlanRepository studyPlanRepository;
//
//    @Autowired
//    SemesterRepository semesterRepository;
//
//    @Autowired
//    MandatoryBlockRepository mandatoryBlockRepository;
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Autowired
//    CourseMandBlockAssignRepository courseMandBlockAssignRepository;
//
//    @Autowired
//    ClassGroupRepository classGroupRepository;
//
//    @Autowired
//    StudentRepository studentRepository;
//
//    @Autowired
//    TeacherRepository teacherRepository;
//
//    @Autowired
//    ClassPlanRepository classPlanRepository;
//
//    @EventListener
//    public void writeDataToDatabase(ApplicationReadyEvent event){
//        Faker faker = new Faker();

//        //DANE OSOBISTE
//
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
//        Semester semester = new Semester();
//        semester.setSemesterNumber(2);
//        semester.setStudyPlanCode(studyPlanRepository.findAll().get(0).getStudyPlanCode());
//        semesterRepository.save(semester);
//
//        //BLOK OBOWIAZKOWY
//        MandatoryBlock mandatoryBlock = new MandatoryBlock();
//        mandatoryBlock.setName("Blok obowiazkowy");
//        mandatoryBlock.setSemesterId(semesterRepository.findAll().get(0).getId());
//        mandatoryBlockRepository.save(mandatoryBlock);
//
//        mandatoryBlock = mandatoryBlockRepository.findAll().get(0);
//
//        //KURSY
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
//        //PRZYPISANIE KURSU DO BLOKU
//        List<Course> courses = courseRepository.findAll();
//        for(Course c : courses){
//            CourseMandBlockAssign mandBlockAssign = new CourseMandBlockAssign();
//            mandBlockAssign.setMandBlockId(mandatoryBlock.getId());
//            mandBlockAssign.setCourseCode(c.getCourseCode());
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

        //STUDENCI
//        List<Account> accounts = accountRepository.findAll();
//        for(Account a : accounts){
//            if(a.getAccountTypeId().equals(4L)){
//                Student student = new Student();
//                student.setStudyStartDate(LocalDate.parse("2019-09-01"));
//                student.setAccountId(a.getId());
//                studentRepository.save(student);
//            }
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
//    }
}