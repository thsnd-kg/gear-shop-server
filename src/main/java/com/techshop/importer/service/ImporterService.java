package com.techshop.importer.service;

import com.techshop.importer.dto.CreateImporterDto;
import com.techshop.importer.dto.GetImporterDetailDto;
import com.techshop.importer.dto.GetImporterDto;
import com.techshop.importer.entity.Importer;
import com.techshop.importer.entity.ImporterDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ImporterService {
    List<GetImporterDto> getImports();
    GetImporterDto getImport(Long importId);
    GetImporterDto createImport(CreateImporterDto dto);
    Map<LocalDate, List<Importer>> getImportReport(LocalDate start, LocalDate end, String compression);

    List<GetImporterDetailDto> getImportDetail(Set<ImporterDetail> importDetails);
}
