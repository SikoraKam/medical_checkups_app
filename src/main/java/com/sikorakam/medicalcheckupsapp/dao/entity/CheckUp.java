package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.sikorakam.medicalcheckupsapp.dao.Category;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "checkup")
public class CheckUp {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
    @Column(name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkup")
    private Result result;



    public CheckUp(){
    }

    public CheckUp(Category category, LocalDate date, Double result) {
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
