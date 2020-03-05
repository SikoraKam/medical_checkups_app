package com.sikorakam.medicalcheckupsapp.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "checkup")
public class CheckUp {

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "result")
    private Double result;

    public CheckUp(){
    }

    public CheckUp(String category, LocalDate date, Double result) {
        this.category = category;
        this.date = date;
        this.result = result;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
