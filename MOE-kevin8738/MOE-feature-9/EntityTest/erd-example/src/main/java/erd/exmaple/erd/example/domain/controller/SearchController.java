package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.dto.SearchResultDTO;
import erd.exmaple.erd.example.domain.service.SearchService.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search/{userId}")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public List<SearchResultDTO> search(@PathVariable Long userId, @RequestParam String keyword, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6);
        return searchService.search(keyword, userId, pageable);
    }
}
