package com.htakemoto.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.htakemoto.domain.Item;
import com.htakemoto.domain.User;
import com.htakemoto.service.exception.ItemAlreadyExistsException;
import com.htakemoto.service.exception.NoItemExistsException;
import com.htakemoto.service.exception.NoUserExistsException;

@Service
@Validated
public class ItemServiceImpl implements ItemService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Inject
    public ItemServiceImpl(final ItemRepository itemRepository, final UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<Item> save(@NotNull @Valid final List<Item> items, @NotNull @Valid final long userId) {
        LOGGER.debug("Creating {} on userId={}", items, userId);
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        List<Item> itemsWithUser = new ArrayList<Item>();
        for (Item item : items) {
            if (itemRepository.exists(item.getItemId())) {
                throw new ItemAlreadyExistsException (
                        String.format("There already exists an item with id=%d", item.getItemId()));
            }
            item.setUser(user);
            itemsWithUser.add(item);
        }
        return itemRepository.save(itemsWithUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findByUserId(@NotNull @Valid final long userId) {
        LOGGER.debug("Retrieving the list of all items on UserId={}", userId);
        if (!userRepository.exists(userId)) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        return itemRepository.findByUserId(userId);
    }
    
    @Override
    @Transactional
    public Item update(@NotNull @Valid final Item item, @NotNull @Valid final long itemId, @NotNull @Valid final long userId) {
        LOGGER.debug("Updating {} on ItemId={} UserId={}", item, itemId, userId);
        User user =  userRepository.findOne(userId);
        if (user == null) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        if (!itemRepository.exists(itemId)) {
            throw new NoItemExistsException (
                    String.format("No item exists with id=%d", itemId));
        }
        item.setItemId(itemId);
        item.setUser(user);
        return itemRepository.save(item);
    }
    
    @Override
    @Transactional
    public Item delete(@NotNull @Valid final long itemId, @NotNull @Valid final long userId) {
        LOGGER.debug("Deleting {} on UserId={}", itemId, userId);
        if (!userRepository.exists(userId)) {
            throw new NoUserExistsException (
                    String.format("No user exists with id=%d", userId));
        }
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            throw new NoItemExistsException (
                    String.format("No item exists with id=%d", itemId));
        }
        itemRepository.delete(itemId);
        return item;
    }
}
