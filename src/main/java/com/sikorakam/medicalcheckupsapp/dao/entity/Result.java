package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "results")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkup_id")
    private CheckUp checkUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public Result() {
    }

    public Result(Double value) {
        this.value = value;
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
    @JsonIgnore
    public CheckUp getCheckUp() {
        return checkUp;
    }

    public void setCheckUp(CheckUp checkUp) {
        this.checkUp = checkUp;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test tests) {
        this.test = tests;
    }

    public Long getCheckUp_Id(){
        return checkUp.getId();
    }
}
