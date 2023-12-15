package ru.practicum.item;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ItemDtoRequest {
    private String url;
    private Set<String> tags = new HashSet<>();
}
