package com.htakemoto.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private long userId;
    
    @Column(name="first_name", nullable=false, length=50)
    private String firstname;
    
    @Column(name="last_name", length=20)
    private String lastname;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    private List<Item> items;

    // The default constructor only exists for the sake of JPA.
    // You won’t use it directly, so it is designated as protected.
    protected User() {}
}
