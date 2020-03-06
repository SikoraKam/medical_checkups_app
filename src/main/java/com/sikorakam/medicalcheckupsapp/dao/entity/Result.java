package com.sikorakam.medicalcheckupsapp.dao.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hpv")
    private Double hpv;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "checkUp_id", nullable = false)
    private CheckUp checkUp;

    public Result() {
    }

    public Result(Double hpv, CheckUp checkUp) {
        this.hpv = hpv;
        this.checkUp = checkUp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHpv() {
        return hpv;
    }

    public void setHpv(Double hpv) {
        this.hpv = hpv;
    }

}
