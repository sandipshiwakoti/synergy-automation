package com.synergy.hrm.model.EmployeeEmbeddables;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class PersonalInformation {
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;
}