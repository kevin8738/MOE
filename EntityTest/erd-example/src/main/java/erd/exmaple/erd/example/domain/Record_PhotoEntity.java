package erd.exmaple.erd.example.domain;

import erd.exmaple.erd.example.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name="record_photo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//전시회,팝업스토어 기록에서 인증할때 사진
public class Record_PhotoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String photo;

    @Column(length = 100)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_page_id")
    private Record_PageEntity record_page;


}
