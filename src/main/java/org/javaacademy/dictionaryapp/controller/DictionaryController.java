package org.javaacademy.dictionaryapp.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionaryapp.dto.PageDto;
import org.javaacademy.dictionaryapp.dto.WordDtoRq;
import org.javaacademy.dictionaryapp.dto.WordDtoRs;
import org.javaacademy.dictionaryapp.service.DictionaryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionary")
@CacheConfig(cacheNames = "portionWords")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @PostMapping
    @CacheEvict(cacheNames = "portionWords", allEntries = true)
    public ResponseEntity<WordDtoRs> addWordToDb(@RequestBody WordDtoRq dto) {
        return ResponseEntity.status(CREATED).body(dictionaryService.addWordToDb(dto));
    }

    @GetMapping
    public List<WordDtoRs> getAllBooks(@RequestParam(required = false) boolean refresh) {
        return dictionaryService.getAllWords();
    }

    @GetMapping("/{name}")
    public WordDtoRs getWordByName(@PathVariable String name) {
        return dictionaryService.getWordsByName(name);
    }

    @DeleteMapping("/{name}")
    @CacheEvict(cacheNames = "portionWords", allEntries = true)
    public ResponseEntity<?> deleteWordByName(@PathVariable String name) {
        boolean result = dictionaryService.deleteByName(name);
        return  result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @PatchMapping("/{name}")
    @CacheEvict(cacheNames = "portionWords", allEntries = true)
    public ResponseEntity<WordDtoRs> patchWordByName(@PathVariable String name,@RequestBody WordDtoRq dto) {
        return ResponseEntity.status(ACCEPTED).body(dictionaryService.patch(name, dto));
    }

    @PutMapping("/{name}")
    @CacheEvict(cacheNames = "portionWords", allEntries = true)
    public ResponseEntity<?> updateBook(@PathVariable String name,@RequestBody WordDtoRq dto) {
        dictionaryService.patch(name, dto);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @GetMapping("/page")
    @Cacheable(cacheNames = "portionWords")
    public PageDto<List<WordDtoRs>> getPages(@RequestParam Integer startElement,
                                             @RequestParam Integer pageSize) {
        return dictionaryService.getPages(startElement, pageSize);
    }
}
