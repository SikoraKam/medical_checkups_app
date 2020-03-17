package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.sikorakam.medicalcheckupsapp.dao.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;


@Entity
@Table(name = "checkUps")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CheckUp {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Id
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkUp")
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "checkup_test", joinColumns = @JoinColumn(name = "checkup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"))
    private Set<Test> tests;


    public CheckUp(){
    }

    public CheckUp( LocalDate date) {

        this.date = date;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
