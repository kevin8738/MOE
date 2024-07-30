package erd.exmaple.erd.example.domain.dto;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import lombok.*;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33

import java.time.LocalDate;
import java.time.LocalDateTime;

<<<<<<< HEAD
=======
@Data
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopupStoreDTO {
    private Long id;
    private String name;
<<<<<<< HEAD
=======
    private String place;
    private String description;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
    private String photoUrl; // 사진 URL 필드 추가
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean heart;
<<<<<<< HEAD
    private String place;
    private String description;
=======
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
    private LocalDateTime searchDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String regions;
    private String district;

}
