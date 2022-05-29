package com.techshop.importer.repository;


import com.techshop.importer.entity.ImporterDetail;
import com.techshop.importer.entity.ImporterDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImporterDetailRepository extends JpaRepository<ImporterDetail, ImporterDetailPK> {
}
