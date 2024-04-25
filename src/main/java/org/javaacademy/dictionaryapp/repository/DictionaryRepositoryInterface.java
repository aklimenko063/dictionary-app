package org.javaacademy.dictionaryapp.repository;

import org.javaacademy.dictionaryapp.entity.Word;
import java.util.List;
import java.util.Optional;

public interface DictionaryRepositoryInterface {

    Word addWordToDb(Word word);

    List<Word> getAllWords();

    Optional<Word> getWordsByName(String name);

    boolean deleteByName(String name);

    boolean deleteWord(Word word);

    List<Word> getPages(Integer startElement, Integer pageSize);
}
