package erd.exmaple.erd.example.domain.repository;


import erd.exmaple.erd.example.domain.Popup_StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopupStoreRepository extends JpaRepository<Popup_StoreEntity, Long> {
    List<Popup_StoreEntity> findByNameContaining(String keyword);
    Page<Popup_StoreEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Popup_StoreEntity> findAllByOrderByLikesCountDesc(Pageable pageable);
}
