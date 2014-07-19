package com.htakemoto.repository;

import java.util.List;

import com.htakemoto.domain.Item;

public interface ItemService {

	List<Item> save(List<Item> items, Long userId);
    List<Item> findByUserId(Long userId);
    Item update(Item item, Long itemId, Long userId);
    Item delete(Long itemId, Long userId);
}
