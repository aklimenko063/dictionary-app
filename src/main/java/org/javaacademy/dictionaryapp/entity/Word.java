package org.javaacademy.dictionaryapp.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Word implements Comparable<Word> {
    @NonNull
    private String name;
    @NonNull
    private String description;

    @Override
    public int compareTo(Word o) {
        return this.name.compareTo(o.name);
    }

}
