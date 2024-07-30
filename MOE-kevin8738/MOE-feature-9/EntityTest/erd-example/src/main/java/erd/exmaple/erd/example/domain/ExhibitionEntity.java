package erd.exmaple.erd.example.domain;

import erd.exmaple.erd.example.domain.common.BaseEntity;
<<<<<<< HEAD
=======
import erd.exmaple.erd.example.domain.enums.District;
import erd.exmaple.erd.example.domain.enums.Region;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "exhibition")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExhibitionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    private String place;

<<<<<<< HEAD
=======
    private String photoUrl;

>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private LocalDateTime searchDate;

<<<<<<< HEAD
=======
    private boolean heart;

>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
    private int likesCount = 0;


    @OneToMany(mappedBy = "exhibition",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<FollowEntity> FollowEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Record_PageEntity> recordPageEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "exhibition",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PhotoEntity> PhotoEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    private RegionEntity region;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SearchEntity> searchEntities = new ArrayList<>();

    public void incrementLikesCount() {
        this.likesCount++;
    }
<<<<<<< HEAD
=======

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region regions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private District district;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
}
