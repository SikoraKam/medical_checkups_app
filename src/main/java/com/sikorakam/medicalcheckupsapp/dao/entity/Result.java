package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.google.common.collect.Range;

import javax.persistence.*;

@Entity
@Table(name = "results")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "value")
    private Double value;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "checkup_id", nullable = false)
    private CheckUp checkUp;

    @Column(name = "test_id")
    private Long testId;


    public Result() {
    }

    public Result(Double value) {
        this.value = value;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CheckUp getCheckUp() {
        return checkUp;
    }

    public void setCheckUp(CheckUp checkUp) {
        this.checkUp = checkUp;
    }
}
