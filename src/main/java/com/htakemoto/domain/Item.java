package com.htakemoto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="item")
@Data
public class Item {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="item_id")
    private long itemId;
    
    @Column(name="item_name", nullable=false, length=50)
    private String itemName;
    
    @Column(name="quantity", nullable=false)
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    @JsonIgnore // Prevent JSON infinite recursion
    public User getUser() {
        return user;
    }

    // The default constructor only exists for the sake of JPA.
    // You won’t use it directly, so it is designated as protected.
    protected Item() {}
}
