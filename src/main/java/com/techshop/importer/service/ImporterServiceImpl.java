package com.techshop.importer.service;


import com.techshop.common.util.AdjusterUtils;
import com.techshop.importer.converter.ImportConverter;
import com.techshop.importer.dto.CreateImporterDetailDto;
import com.techshop.importer.dto.CreateImporterDto;
import com.techshop.importer.dto.GetImporterDetailDto;
import com.techshop.importer.dto.GetImporterDto;
import com.techshop.importer.entity.Importer;
import com.techshop.importer.entity.ImporterDetail;
import com.techshop.importer.repository.ImporterRepository;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Variant;
import com.techshop.product.service.VariantService;
import com.techshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImporterServiceImpl implements ImporterService {
    private  ImporterRepository repository;
    private  VariantService variantService;
    private  UserService userService;
    private ImportConverter importConverter;

    @Autowired
    public ImporterServiceImpl(ImporterRepository repository, @Lazy VariantService variantService, UserService userService, @Lazy ImportConverter importConverter) {
        this.repository = repository;
        this.variantService = variantService;
        this.userService = userService;
        this.importConverter = importConverter;
    }

    @Override
    public List<GetImporterDto> getImports() {
        List<Importer> importers = repository.findAll();
        List<GetImporterDto> result = new ArrayList<>();
        importers.forEach(item -> result.add(importConverter.toGetImportDto(item)));

        return result;
    }

    @Override
    public GetImporterDto getImport(Long importId) {
        Importer result = repository.findById(importId).orElse(null);

        return importConverter.toGetImportDto(result);
    }

    @Override
    public GetImporterDto createImport(CreateImporterDto dto) {
        Importer importer = new Importer();

        if (dto.getImportDesc() != null) {
            importer.setImportDesc(dto.getImportDesc());
        }

        importer.setUser(userService.getProfile());

        for (CreateImporterDetailDto createImporterDetail : dto.getImportDetails()) {
            ImporterDetail importerDetail = new ImporterDetail();

            Variant variant = variantService.getById(createImporterDetail.getVariantId());

            Integer oldQuantity = variant.getQuantity();

            importerDetail.setQuantity(createImporterDetail.getQuantity());
            importerDetail.setPrice(createImporterDetail.getPrice());
            importerDetail.setImporter(importer);
            importerDetail.setVariant(variant);

            importerDetail.getVariant().setQuantity(oldQuantity + importerDetail.getQuantity());
            importerDetail.getVariant().setImportPrice(importerDetail.getPrice());

            importer.getImportDetails().add(importerDetail);
        }

        Importer result = repository.save(importer);

        return importConverter.toGetImportDto(result);
    }

    @Override
    public Map<LocalDate, List<Importer>> getImportReport(LocalDate start, LocalDate end, String compression) {

        return repository.findByCreatedAtBetweenOrderByCreatedAt(start.atStartOfDay(), end.atTime(LocalTime.MAX))
                .stream().collect(Collectors.groupingBy(item ->
                        item.getCreatedAt().toLocalDate().with(AdjusterUtils.getAdjuster().get(compression))));
    }

    @Override
    public List<GetImporterDetailDto> getImportDetail(Set<ImporterDetail> importDetails) {
        List<GetImporterDetailDto> result = new ArrayList<>();
        importDetails.forEach(detail -> {
            GetImporterDetailDto item = new GetImporterDetailDto();
            item.setQuantity(detail.getQuantity());
            item.setPrice(detail.getPrice());

            VariantWithAttributesDto variant = variantService.getVariantDetailById(detail.getVariant().getVariantId());
            item.setVariant(variant);

            result.add(item);
        });

        return result;
    }

    @Override
    public Object getTotalCost() {
        List<Importer> importers = repository.findAll();
        return new HashMap<String, Object>() {{
            put("count_import", importers.size());
            put("total_cost", importers.stream().map(Importer::getTotalPrice).mapToLong(Long::longValue).sum());
        }};
    }
}
