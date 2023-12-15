package ru.practicum.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.ForbidenException;
import ru.practicum.user.User;
import ru.practicum.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final UserRepository userRepository;
    private final UrlMetaDataRetrieverImpl urlMetaDataRetriever;

    @Override
    public List<ItemDto> getItems(long userId) {
        List<Item> userItems = repository.findByUserId(userId);
        return ItemMapper.mapToItemDto(userItems);
    }

    @Transactional
    @Override
    public ItemDto addNewItem(long userId, ItemDtoRequest itemDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ForbidenException("You do not have permission to perform this operation"));

        UrlMetaDataRetriever.UrlMetadata result = urlMetaDataRetriever.retrieve(itemDto.getUrl());

        Optional<Item> maybeExistingItem = repository.findByUserAndResolvedUrl(user, result.getResolvedUrl());
        Item item;
        if (maybeExistingItem.isEmpty()) {
            item = repository.save(ItemMapper.mapToItem(result, user, itemDto.getTags()));
        } else {
            item = maybeExistingItem.get();
            if (itemDto.getTags() != null && !itemDto.getTags().isEmpty()) {
                item.getTags().addAll(itemDto.getTags());
                repository.save(item);
            }
        }
        return ItemMapper.mapToItemDto(item);
    }

    @Transactional
    @Override
    public void deleteItem(long userId, long itemId) {
        repository.deleteByUserIdAndId(userId, itemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> getItems(long userId, Set<String> tags) {
        BooleanExpression byUserId = QItem.item.user.id.eq(userId);
        BooleanExpression byAnyTag = QItem.item.tags.any().in(tags);
        Iterable<Item> foundItems = repository.findAll(byUserId.and(byAnyTag));
        return ItemMapper.mapToItemDto(foundItems);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> getUserItems(String lastName) {
        List<Item> foundItems = repository.findItemsByLastNamePrefix(lastName);
        return ItemMapper.mapToItemDto(foundItems);
    }
}
