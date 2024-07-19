package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.PhotoEntity;
import erd.exmaple.erd.example.domain.Record_PageEntity;
import erd.exmaple.erd.example.domain.Record_PhotoBodyEntity;
import erd.exmaple.erd.example.domain.Record_PhotoEntity;
import erd.exmaple.erd.example.domain.service.RecordService.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/record/oldest/{userId}")
public class RecordOldestController {
    private final RecordService recordService;

    @GetMapping
    public String getRecordList(@PathVariable Long userId, Pageable pageable, Model model) {
        Page<Record_PageEntity> records = recordService.getPagedRecords(PageRequest.of(pageable.getPageNumber(), 4), true, userId);
        List<PhotoEntity> photos = recordService.getPhotosByRecordPages(records.getContent());
        model.addAttribute("records", records);
        model.addAttribute("photos", photos);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("totalPages", records.getTotalPages());
        model.addAttribute("userId", userId);
        return "record_list_oldest";
    }
    @GetMapping("/{id}")
    public String getExhibitionOrPopupDetails(@PathVariable Long userId, @PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model) {
        Record_PageEntity recordPage = recordService.getRecordPageById(id, userId);
        Page<Record_PhotoEntity> recordPhotos = recordService.getRecordPhotosByPageId(id, PageRequest.of(page, 4), userId);

        PhotoEntity photo = recordService.getPhotoByRecordPage(recordPage); // 사진을 가져오는 서비스 메서드 추가

        model.addAttribute("recordPage", recordPage);
        model.addAttribute("recordPhotos", recordPhotos);
        model.addAttribute("photo", photo); // 사진을 모델에 추가
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", recordPhotos.getTotalPages());
        model.addAttribute("userId", userId);
        return "exhibition_or_popup_details";
    }

    @GetMapping("/{id}/photo/{photoId}")
    public String getRecordPhotoDetails(@PathVariable Long userId, @PathVariable Long id, @PathVariable Long photoId, Model model) {
        Record_PhotoEntity recordPhoto = recordService.getRecordPhotoById(photoId, userId);
        Record_PhotoBodyEntity recordPhotoBody = recordService.getRecordPhotoBodyByPhotoId(photoId);
        model.addAttribute("recordPhoto", recordPhoto);
        model.addAttribute("recordPhotoBody", recordPhotoBody);
        model.addAttribute("userId", userId);
        return "record_photo_details";
    }
}
