package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RDTs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RDT {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String lga;
    private String patientNo;
    private String age;
    private String sex;
    private String patientType;
    private String symptomOnset;
    private LocalDate lastContact;
    private LocalDateTime sampleCollection;
    private String sampleCollectedType;
    private String testType;
    private String testLotNo;
    private LocalDateTime resultDateTime;
    private String testResult;
    private String symptomaticCase;
    private String stateOfContacts;
    private LocalDateTime dateOfDispatch;
    private String sampleNo;
    private String laboratory;
    private LocalDateTime molecularTestResultDateTime;
    private String molecularTestResult;
}
