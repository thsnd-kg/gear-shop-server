package com.techshop.importer.repository;

import com.techshop.importer.entity.Importer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ImporterRepository extends JpaRepository<Importer, Long> {
    List<Importer> findByCreatedAtBetweenOrderByCreatedAt(LocalDateTime start, LocalDateTime end);
}
