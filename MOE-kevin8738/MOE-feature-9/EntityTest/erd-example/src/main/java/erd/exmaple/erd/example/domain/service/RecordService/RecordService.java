package erd.exmaple.erd.example.domain.service.RecordService;

import erd.exmaple.erd.example.domain.PhotoEntity;
import erd.exmaple.erd.example.domain.Record_PageEntity;
import erd.exmaple.erd.example.domain.Record_PhotoBodyEntity;
import erd.exmaple.erd.example.domain.Record_PhotoEntity;
import erd.exmaple.erd.example.domain.repository.PhotoRepository;
import erd.exmaple.erd.example.domain.repository.RecordPageRepository;
import erd.exmaple.erd.example.domain.repository.RecordPhotoBodyRepository;
import erd.exmaple.erd.example.domain.repository.RecordPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordPageRepository recordPageRepository;
    private final RecordPhotoRepository recordPhotoRepository;
    private final PhotoRepository photoRepository;
    private final RecordPhotoBodyRepository recordPhotoBodyRepository;

    public Page<Record_PageEntity> getPagedRecords(Pageable pageable, boolean isOldest, Long userId) {
        return isOldest ? recordPageRepository.findAllByUserIdOrderByCreatedAtAsc(pageable, userId) : recordPageRepository.findAllByUserIdOrderByCreatedAtDesc(pageable, userId);
    }

    public Record_PageEntity getRecordPageById(Long id, Long userId) {
        return recordPageRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public Page<Record_PhotoEntity> getRecordPhotosByPageId(Long pageId, Pageable pageable, Long userId) {
        return recordPhotoRepository.findByRecordPage_IdAndRecordPage_User_Id(pageId, userId, pageable);
    }

    public Record_PhotoEntity getRecordPhotoById(Long photoId, Long userId) {
        return recordPhotoRepository.findByIdAndRecordPage_User_Id(photoId, userId).orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    public PhotoEntity getPhotoByRecordPage(Record_PageEntity recordPage) {
        if (recordPage.getExhibition() != null) {
            return photoRepository.findByExhibition(recordPage.getExhibition()).orElse(null);
        } else if (recordPage.getPopupStore() != null) {
            return photoRepository.findByPopupStore(recordPage.getPopupStore()).orElse(null);
        }
        return null;
    }
    public List<PhotoEntity> getPhotosByRecordPages(List<Record_PageEntity> recordPages) {
        return recordPages.stream()
                .map(this::getPhotoByRecordPage)
                .collect(Collectors.toList());
    }
    public Record_PhotoBodyEntity getRecordPhotoBodyByPhotoId(Long photoId) {
        return recordPhotoBodyRepository.findByRecordPhoto_Id(photoId).orElseThrow(() -> new RuntimeException("Photo body not found"));
    }
}
