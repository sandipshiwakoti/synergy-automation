package com.synergy.hrm.model.EmployeeEmbeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class FamilyInformation {
    @Column(name = "grandfather_name")
    private String grandfatherName;
    @Column(name = "grandmother_name")
    private String grandmotherName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "spouse_name")
    private String spouseName;
}