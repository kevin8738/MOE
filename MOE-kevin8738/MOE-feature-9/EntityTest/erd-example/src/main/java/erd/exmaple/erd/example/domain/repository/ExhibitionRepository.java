package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.ExhibitionEntity;
<<<<<<< HEAD
=======
import erd.exmaple.erd.example.domain.enums.District;
import erd.exmaple.erd.example.domain.enums.Region;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExhibitionRepository extends JpaRepository<ExhibitionEntity, Long> {
    List<ExhibitionEntity> findByNameContaining(String keyword);
    Page<ExhibitionEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<ExhibitionEntity> findAllByOrderByLikesCountDesc(Pageable pageable);
<<<<<<< HEAD
=======
    List<ExhibitionEntity> findByRegions(Region regions);
    List<ExhibitionEntity> findByDistrict(District district);
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
}
