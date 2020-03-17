package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "testName")
    private String testName;
    @Column(name  = "min")
    private Double min;
    @Column(name = "max")
    private Double max;

    @ManyToMany(mappedBy = "tests", fetch = FetchType.LAZY)
    private Set<CheckUp> checkUps;

    public Test() {
    }

    public Test(String category, String testName, Double min, Double max) {
        this.category = category;
        this.testName = testName;
        this.min = min;
        this.max = max;
    }

    public Test(String category, String testName, Double min, Double max, Set<CheckUp> checkUps) {
        this.category = category;
        this.testName = testName;
        this.min = min;
        this.max = max;
        this.checkUps = checkUps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Set<CheckUp> getCheckUps() {
        return checkUps;
    }

    public void setCheckUps(Set<CheckUp> checkUps) {
        this.checkUps = checkUps;
    }
}
