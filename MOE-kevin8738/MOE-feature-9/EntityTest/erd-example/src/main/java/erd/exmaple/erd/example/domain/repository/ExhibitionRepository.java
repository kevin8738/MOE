package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.ExhibitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExhibitionRepository extends JpaRepository<ExhibitionEntity, Long> {
    List<ExhibitionEntity> findByNameContaining(String keyword);
    Page<ExhibitionEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
