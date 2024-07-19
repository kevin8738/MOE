package erd.exmaple.erd.example.domain;

import erd.exmaple.erd.example.domain.common.BaseEntity;
import erd.exmaple.erd.example.domain.enums.Heart;
import erd.exmaple.erd.example.domain.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name="record_page")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Record_PageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private RecordStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitionId")
    private ExhibitionEntity exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "popupStoreId")
    private Popup_StoreEntity popupStore;

    @OneToMany(mappedBy = "recordPage", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Record_PhotoEntity> recordPhotoEntityList = new ArrayList<>();

    @Transient
    public String getPhotoUrl() {
        if (recordPhotoEntityList != null && !recordPhotoEntityList.isEmpty()) {
            return recordPhotoEntityList.get(0).getPhotoUrl(); // 첫 번째 사진의 URL을 반환합니다.
        }
        return null;
    }

    @Transient
    public boolean isHeart() {
        if (exhibition != null) {
            return exhibition.getFollowEntityList().stream()
                    .anyMatch(follow -> follow.getUser().getId().equals(user.getId()) && follow.getHeart() == Heart.ACTIVE);
        } else if (popupStore != null) {
            return popupStore.getFollowEntityList().stream()
                    .anyMatch(follow -> follow.getUser().getId().equals(user.getId()) && follow.getHeart() == Heart.ACTIVE);
        }
        return false;
    }
}
