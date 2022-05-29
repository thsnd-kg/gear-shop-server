package com.techshop.importer.converter;

import com.techshop.importer.dto.GetImporterDetailDto;
import com.techshop.importer.dto.GetImporterDto;
import com.techshop.importer.entity.Importer;
import com.techshop.importer.entity.ImporterDetail;
import com.techshop.importer.service.ImporterService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportConverter {
    private ImporterService importerService;

    public ImportConverter(@Lazy ImporterService importerService){
        this.importerService = importerService;
    }

    public GetImporterDto toGetImportDto (Importer importer){
            GetImporterDto result = new GetImporterDto();

        result.setImportId(importer.getImportId());
        result.setImportDesc(importer.getImportDesc());
        result.setTotalPrice(importer.getTotalPrice());
        result.setEmailImporter(importer.getUser().getEmail());
        result.setCreatedAt(importer.getCreatedAt().toLocalDate());

        List<GetImporterDetailDto> importerDetailDtos = importerService.getImportDetail(importer.getImportDetails());
        result.setImportDetails(importerDetailDtos);

        return result;
    }
}
