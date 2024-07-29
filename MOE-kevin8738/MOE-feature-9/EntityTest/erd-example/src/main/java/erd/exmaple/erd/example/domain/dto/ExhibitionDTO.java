package erd.exmaple.erd.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionDTO {
    private Long id;
    private String name;
    private String photoUrl; // 사진 URL 필드 추가
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean heart;
    private String place;
    private String description;
    private LocalDateTime searchDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String regions;
    private String district;

}
