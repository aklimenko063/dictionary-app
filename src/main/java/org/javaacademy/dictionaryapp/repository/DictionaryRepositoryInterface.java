package org.javaacademy.dictionaryapp.repository;

import org.javaacademy.dictionaryapp.entity.Word;
import java.util.List;
import java.util.Optional;

public interface DictionaryRepositoryInterface {

    public Word addWordToDb(Word word);

    public List<Word> getAllWords();

    public Optional<Word> getWordsByName(String name);

    public boolean deleteByName(String name);

    public boolean deleteWord(Word word);

    public List<Word> getPages(Integer startElement, Integer pageSize);
}
