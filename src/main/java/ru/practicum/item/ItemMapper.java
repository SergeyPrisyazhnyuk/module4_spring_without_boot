package ru.practicum.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ItemMapper {
    public static Item mapToItem(ItemDto itemDto, User user) {
        Item item = new Item();
        item.setUser(user);
        item.setUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        return item;
    }

    public static Item mapToItem(UrlMetaDataRetriever.UrlMetadata result, User user, Set<String> tags) {
        Item item = new Item();
        item.setUser(user);
        item.setUrl(result.getNormalUrl());
        item.setResolvedUrl(result.getResolvedUrl());
        item.setMimeType(result.getMimeType());
        item.setTitle(result.getTitle());
        item.setHasImage(result.isHasImage());
        item.setHasVideo(result.isHasVideo());
        item.setDateResolved(result.getDateResolved());
        item.setTags(tags);
        return item;
    }
    public static ItemDto mapToItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getUser().getId(),
                item.getUrl(),
                new HashSet<>(item.getTags())
        );
    }

    public static List<ItemDto> mapToItemDto(Iterable<Item> items) {
        List<ItemDto> dtos = new ArrayList<>();
        for (Item item : items) {
            dtos.add(mapToItemDto(item));
        }
        return dtos;
    }
}
