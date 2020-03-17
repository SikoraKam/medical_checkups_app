package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Result> results;

    public Test() {
    }

    public Test(String category, String testName, Double min, Double max) {
        this.category = category;
        this.testName = testName;
        this.min = min;
        this.max = max;
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
    @JsonIgnore
    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
}
