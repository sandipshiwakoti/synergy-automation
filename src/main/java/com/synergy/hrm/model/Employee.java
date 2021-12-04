package com.synergy.hrm.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.synergy.hrm.model.EmployeeEmbeddables.AddressInformation;
import com.synergy.hrm.model.EmployeeEmbeddables.FamilyInformation;
import com.synergy.hrm.model.EmployeeEmbeddables.PersonalInformation;
import com.synergy.hrm.model.EmployeeEmbeddables.HealthInformation;

import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private long id;
        @Embedded
        private PersonalInformation personalInformation;
        @Embedded
        private FamilyInformation familyInformation;
        @Embedded
        @AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "permanent_country")),
                        @AttributeOverride(name = "province", column = @Column(name = "permanent_province")),
                        @AttributeOverride(name = "district", column = @Column(name = "permanent_district")),
                        @AttributeOverride(name = "municipality", column = @Column(name = "permanent_municipality")),
                        @AttributeOverride(name = "tole", column = @Column(name = "permanent_tole")),
                        @AttributeOverride(name = "ward", column = @Column(name = "permanent_ward")) })
        private AddressInformation permanentAddressInformation;
        @Embedded
        @AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "temporary_country")),
                        @AttributeOverride(name = "province", column = @Column(name = "temporary_province")),
                        @AttributeOverride(name = "district", column = @Column(name = "temporary_district")),
                        @AttributeOverride(name = "municipality", column = @Column(name = "temporary_municipality")),
                        @AttributeOverride(name = "tole", column = @Column(name = "temporary_tole")),
                        @AttributeOverride(name = "ward", column = @Column(name = "temporary_ward")) })
        private AddressInformation temporaryAddressInformation;
        @Embedded
        private HealthInformation healthInformation;

        @OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL })
        private List<Attendance> attendances;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "job_id", nullable = false)
        @JsonIgnore
        private Job job;
        @OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL })
        private List<Leave> leaves;
}
