package com.techshop.importer.dto;

import com.techshop.importer.entity.Importer;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetImporterDto {
    private  Long importId;
    private  String importDesc;
    private  Long totalPrice;
    private  String emailImporter;
    private  LocalDate createdAt;
    private  List<GetImporterDetailDto> importDetails;

}
