package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.Record_PhotoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordPhotoRepository extends JpaRepository<Record_PhotoEntity, Long> {
    Page<Record_PhotoEntity> findByRecordPage_IdAndRecordPage_User_Id(Long pageId, Long userId, Pageable pageable);
    Optional<Record_PhotoEntity> findByIdAndRecordPage_User_Id(Long photoId, Long userId);
}
