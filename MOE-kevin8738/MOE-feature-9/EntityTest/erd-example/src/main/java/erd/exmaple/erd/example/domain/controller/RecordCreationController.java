package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.dto.RecordRequestDTO;
import erd.exmaple.erd.example.domain.dto.RecordResponseDTO;
import erd.exmaple.erd.example.domain.service.RecordService.RecordCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordCreationController {
    private final RecordCreationService recordCreationService;

    @PostMapping
    public ResponseEntity<RecordResponseDTO.RecordResultDTO> createOrUpdateRecord(@RequestBody RecordRequestDTO.RecordDTO recordDTO) {
        RecordResponseDTO.RecordResultDTO createdBody = recordCreationService.createOrUpdateRecord(recordDTO);
        return ResponseEntity.ok(createdBody);
    }
}
