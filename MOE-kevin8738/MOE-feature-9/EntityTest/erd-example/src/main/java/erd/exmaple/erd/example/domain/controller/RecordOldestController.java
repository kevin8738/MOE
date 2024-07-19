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
@RequestMapping("/record/{userId}")
public class RecordOldestController {
    private final RecordService recordService;

    @GetMapping("/oldest")
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

}
