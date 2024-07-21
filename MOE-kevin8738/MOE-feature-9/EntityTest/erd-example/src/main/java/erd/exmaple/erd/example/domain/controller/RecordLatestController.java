package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.PhotoEntity;
import erd.exmaple.erd.example.domain.Record_PageEntity;
import erd.exmaple.erd.example.domain.Record_PhotoEntity;
import erd.exmaple.erd.example.domain.Record_PhotoBodyEntity;
import erd.exmaple.erd.example.domain.dto.ExhibitionOrPopupDetailsDTO;
import erd.exmaple.erd.example.domain.dto.RecordPageResponseDTO;
import erd.exmaple.erd.example.domain.service.RecordService.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record/{userId}")
public class RecordLatestController {
    private final RecordService recordService;

    @GetMapping("/latest")
    public List<RecordPageResponseDTO> getLatestRecordList(@PathVariable Long userId, Pageable pageable) {
        return recordService.getPagedRecordsDto(PageRequest.of(pageable.getPageNumber(), 4), false, userId).getContent();
    }

    @GetMapping("/{id}")
    public ExhibitionOrPopupDetailsDTO getExhibitionOrPopupDetails(
            @PathVariable Long userId,
            @PathVariable Long id) {
        return recordService.getExhibitionOrPopupDetails(userId, id);
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
