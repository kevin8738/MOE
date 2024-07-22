package erd.exmaple.erd.example.domain.service.SearchService;
import erd.exmaple.erd.example.domain.*;
import erd.exmaple.erd.example.domain.dto.SearchResultDTO;
import erd.exmaple.erd.example.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ExhibitionRepository exhibitionRepository;
    private final PopupStoreRepository popupStoreRepository;
    private final FollowRepository followRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<SearchResultDTO> search(String keyword, Long userId, Pageable pageable) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<SearchResultDTO> results = new ArrayList<>();

        // Exhibition 검색 및 결과 추가
        List<ExhibitionEntity> exhibitions = exhibitionRepository.findByNameContaining(keyword);
        for (ExhibitionEntity exhibition : exhibitions) {
            updateSearchDate(exhibition);
            PhotoEntity photo = photoRepository.findByExhibition(exhibition).orElse(null);
            boolean followed = followRepository.findByUserAndExhibition(user, exhibition).isPresent();
            results.add(toSearchResultDTO(exhibition, photo, followed));
        }

        // PopupStore 검색 및 결과 추가
        List<Popup_StoreEntity> popupStores = popupStoreRepository.findByNameContaining(keyword);
        for (Popup_StoreEntity popupStore : popupStores) {
            updateSearchDate(popupStore);
            PhotoEntity photo = photoRepository.findByPopupStore(popupStore).orElse(null);
            boolean followed = followRepository.findByUserAndPopupStore(user, popupStore).isPresent();
            results.add(toSearchResultDTO(popupStore, photo, followed));
        }

        // 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), results.size());
        List<SearchResultDTO> pagedResults = results.subList(start, end);

        return pagedResults;
    }

    private void updateSearchDate(ExhibitionEntity exhibition) {
        exhibition.setSearchDate(LocalDateTime.now());
        exhibitionRepository.save(exhibition);
    }

    private void updateSearchDate(Popup_StoreEntity popupStore) {
        popupStore.setSearchDate(LocalDateTime.now());
        popupStoreRepository.save(popupStore);
    }

    private SearchResultDTO toSearchResultDTO(ExhibitionEntity exhibition, PhotoEntity photo, boolean followed) {
        return SearchResultDTO.builder()
                .name(exhibition.getName())
                .photo(photo != null ? photo.getPhoto() : null)
                .startDate(exhibition.getStartDate().atStartOfDay())
                .endDate(exhibition.getEndDate().atStartOfDay())
                .followed(followed)
                .build();
    }

    private SearchResultDTO toSearchResultDTO(Popup_StoreEntity popupStore, PhotoEntity photo, boolean followed) {
        return SearchResultDTO.builder()
                .name(popupStore.getName())
                .photo(photo != null ? photo.getPhoto() : null)
                .startDate(popupStore.getStartDate().atStartOfDay())
                .endDate(popupStore.getEndDate().atStartOfDay())
                .followed(followed)
                .build();
    }
}
