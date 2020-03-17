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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "checkup_id", nullable = false)
    private CheckUp checkUp;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "result_test", joinColumns = @JoinColumn(name = "result_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"))
    private Set<Test> tests;

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

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public Long getCheckUp_Id(){
        return checkUp.getId();
    }
}
