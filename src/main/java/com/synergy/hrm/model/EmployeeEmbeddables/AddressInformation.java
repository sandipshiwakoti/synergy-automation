package com.synergy.hrm.model.EmployeeEmbeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class AddressInformation {
    @Column(name = "country")
    private String country;
    @Column(name = "province")
    private String province;
    @Column(name = "district")
    private String district;
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "tole")
    private String tole;
    @Column(name = "ward")
    private int ward;
}