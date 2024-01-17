package com.example.EduManagmentSystem.enums;

public enum AccountType {
    ADMIN(1L, "admin"),  //ZARZADCA
    DEANS_OFFICE_EMPLOYEE(2L, "deans_office_employee"),  //PRACOWNIK DZIEKANATU
    TEACHER(3L, "teacher"),  //PROWADZACY
    STUDENT(4L, "student")  //STUDENT
    ;

    AccountType(Long id, String name) {

    }
}
