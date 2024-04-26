package org.javaacademy.dictionaryapp.repository;

import org.javaacademy.dictionaryapp.annotation.SleepAnnotation;
import org.javaacademy.dictionaryapp.entity.Word;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public class DictionaryRepository implements DictionaryRepositoryInterface {
    private final TreeSet<Word> dictionaryDB = new TreeSet();

    @Override
    @SleepAnnotation(latency = 3000)
    public Word addWordToDb(Word word) {
        dictionaryDB.add(word);
        return word;
    }

    @Override
    @SleepAnnotation(latency = 3000)
    public List<Word> getAllWords() {
        return new ArrayList<>(dictionaryDB.stream().toList());
    }

    @Override
    @SleepAnnotation(latency = 3000)
    public Optional<Word> getWordsByName(String name) {
        return dictionaryDB.stream()
                        .filter(e -> e.getName().equals(name))
                        .findFirst();
    }

    @Override
    @SleepAnnotation(latency = 3000)
    public boolean deleteByName(String name) {
        return dictionaryDB.remove(getWordsByName(name).orElseThrow());
    }

    @Override
    @SleepAnnotation(latency = 3000)
    public boolean deleteWord(Word word) {
        return dictionaryDB.remove(word);
    }

    @Override
    @SleepAnnotation(latency = 3000)
    public List<Word> getPages(Integer startElement, Integer pageSize) {
        return dictionaryDB.stream().toList();
    }
}
