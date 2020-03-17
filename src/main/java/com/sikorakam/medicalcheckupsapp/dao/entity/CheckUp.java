package com.sikorakam.medicalcheckupsapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "checkUp")
    private Set<Result> results;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    public CheckUp() {
    }

    public CheckUp(LocalDate date) {
        this.date = date;
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

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
    @JsonIgnore
    public Client getClient() {
        return client;
    }
    @JsonIgnore
    public void setClient(Client client) {
        this.client = client;
    }

   //method to print only clientId, not whole entity
    public Long getClient_Id(){
        return client.getId();
    }
}
