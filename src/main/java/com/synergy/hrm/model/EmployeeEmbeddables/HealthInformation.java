package com.synergy.hrm.model.EmployeeEmbeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class HealthInformation {
    @Column(name = "medical_condition")
    private String medicalCondition;
    @Column(name = "is_allergic")
    private String isAllergic;
}
