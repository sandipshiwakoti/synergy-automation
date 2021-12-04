package com.synergy.hrm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue
    private long id;
    private String officeName;
    private String level;
    private String position;
    private String branch;
    private String department;
    private Date joinedDate;
    private int serviceYears;
    private String teamNames;
    private String transferHistoryDetails;

    @OneToOne(mappedBy = "job")
    private Employee employee;
}