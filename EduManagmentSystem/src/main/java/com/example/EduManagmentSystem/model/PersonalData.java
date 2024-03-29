package com.example.EduManagmentSystem.model;

//import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String phoneNumber;
}
