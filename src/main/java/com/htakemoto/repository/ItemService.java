package com.htakemoto.repository;

import java.util.List;

import com.htakemoto.domain.Item;

public interface ItemService {

	List<Item> save(List<Item> items, long userId);
    List<Item> findByUserId(long userId);
    Item update(Item item, long itemId, long userId);
    Item delete(long itemId, long userId);
}
