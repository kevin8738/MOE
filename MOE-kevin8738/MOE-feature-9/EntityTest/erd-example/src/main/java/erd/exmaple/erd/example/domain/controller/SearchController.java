package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.dto.SearchResultDTO;
import erd.exmaple.erd.example.domain.service.SearchService.SearchPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchPageService searchService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SearchResultDTO>> search(@PathVariable Long userId, @RequestParam String keyword, @RequestParam(defaultValue = "0") int page) {
        List<SearchResultDTO> results = searchService.search(keyword, userId, PageRequest.of(page, 6));
        return ResponseEntity.ok(results);
    }

    @GetMapping("/recent/{userId}")
    public ResponseEntity<?> getRecentSearches(@PathVariable Long userId) {
        List<SearchResultDTO> recentSearches = searchService.getRecentSearches(userId);
        if (recentSearches.isEmpty()) {
            return ResponseEntity.ok("최근 검색 기록이 없습니다.");
        }
        return ResponseEntity.ok(recentSearches);
    }
}
