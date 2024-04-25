package org.javaacademy.dictionaryapp.repository;

import lombok.SneakyThrows;
import org.javaacademy.dictionaryapp.entity.Word;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public class DictionaryRepository implements DictionaryRepositoryInterface {
    private final TreeSet<Word> dictionaryDB = new TreeSet();

    @SneakyThrows
    @Override
    public Word addWordToDb(Word word) {
        Thread.sleep(3000);
        dictionaryDB.add(word);
        return word;
    }

    @SneakyThrows
    @Override
    public List<Word> getAllWords() {
        Thread.sleep(3000);
        return new ArrayList<>(dictionaryDB.stream().toList());
    }

    @SneakyThrows
    @Override
    public Optional<Word> getWordsByName(String name) {
        Thread.sleep(3000);
        return dictionaryDB.stream()
                        .filter(e -> e.getName().equals(name))
                        .findFirst();
    }

    @SneakyThrows
    @Override
    public boolean deleteByName(String name) {
        Thread.sleep(3000);
        return dictionaryDB.remove(getWordsByName(name).orElseThrow());
    }

    @SneakyThrows
    @Override
    public boolean deleteWord(Word word) {
        Thread.sleep(3000);
        return dictionaryDB.remove(word);
    }

    @SneakyThrows
    @Override
    public List<Word> getPages(Integer startElement, Integer pageSize) {
        Thread.sleep(3000);
        return dictionaryDB.stream().toList();
    }
}
