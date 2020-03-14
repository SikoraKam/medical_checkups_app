package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.sikorakam.medicalcheckupsapp.dao.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "checkUps")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CheckUp {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
    @Column(name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkUp")
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    public void setClient(Client client) {
        this.client = client;
    }

    public CheckUp(){
    }

    public CheckUp(Category category, LocalDate date) {
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
