package erd.exmaple.erd.example.domain.repository;

import erd.exmaple.erd.example.domain.ExhibitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExhibitionRepository extends JpaRepository<ExhibitionEntity, Long> {
}
