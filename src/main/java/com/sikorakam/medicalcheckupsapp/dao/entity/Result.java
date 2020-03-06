package com.sikorakam.medicalcheckupsapp.dao.entity;

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
    @JoinColumn(name = "checkup_id", nullable = false)
    private CheckUp checkUp;

    public Result() {
    }

    public Result(Double hpv) {
        this.hpv = hpv;
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

    public CheckUp getCheckUp() {
        return checkUp;
    }

    public void setCheckUp(CheckUp checkUp) {
        this.checkUp = checkUp;
    }
}
