package org.javaacademy.dictionaryapp.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryapp.dto.PageDto;
import org.javaacademy.dictionaryapp.dto.WordDtoRq;
import org.javaacademy.dictionaryapp.dto.WordDtoRs;
import org.javaacademy.dictionaryapp.entity.Word;
import org.javaacademy.dictionaryapp.repository.DictionaryRepositoryInterface;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DictionaryRepositoryInterface dictionaryRepositoryInterface;

    public WordDtoRs addWordToDb(WordDtoRq dto) {
        Word word = convertDtoToEntity(dto);
        Word createdWord = dictionaryRepositoryInterface.addWordToDb(word);
        return convertEntityToDto(createdWord);
    }

    public List<WordDtoRs> getAllWords() {
        return dictionaryRepositoryInterface.getAllWords().stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public WordDtoRs getWordsByName(String name) {
        return dictionaryRepositoryInterface.getWordsByName(name)
                .map(this::convertEntityToDto)
                .orElseThrow();
    }

    public boolean deleteByName(String name) {
        return dictionaryRepositoryInterface.deleteByName(name);
    }

    public WordDtoRs patch(String name, WordDtoRq dto) {
        Word word = dictionaryRepositoryInterface.getWordsByName(name).orElseThrow();
        dictionaryRepositoryInterface.deleteWord(word);
        word.setName(dto.getName() != null ? dto.getName() : word.getName());
        word.setDescription(dto.getDescription() != null ? dto.getDescription() : word.getDescription());
        dictionaryRepositoryInterface.addWordToDb(word);
        return convertEntityToDto(word);
    }

    public PageDto<List<WordDtoRs>> getPages(Integer startElement, Integer pageSize) {
        List<Word> words = dictionaryRepositoryInterface.getPages(startElement, pageSize);
        List<WordDtoRs> filteredList = words.stream()
                .skip(startElement)
                .limit(pageSize)
                .map(e -> new WordDtoRs(e.getName(), e.getDescription()))
                .toList();
        return new PageDto<>(
                filteredList.size(),
                words.size(),
                startElement,
                startElement + pageSize,
                filteredList);
    }

    private WordDtoRs convertEntityToDto(Word createdWord) {
        return new WordDtoRs(createdWord.getName(), createdWord.getDescription());
    }

    private Word convertDtoToEntity(WordDtoRq dto) {
        return new Word(dto.getName(), dto.getDescription());
    }
}
