package com.htakemoto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.htakemoto.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.user.userId = ?1")
    List<Item> findByUserId(long userId);
}
