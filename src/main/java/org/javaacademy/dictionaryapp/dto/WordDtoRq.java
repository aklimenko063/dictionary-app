package org.javaacademy.dictionaryapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordDtoRq {
    private String name;
    private String description;
}
