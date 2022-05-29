package com.techshop.importer.dto;


import com.techshop.importer.entity.ImporterDetail;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import lombok.Data;
import lombok.Getter;

@Data
public class GetImporterDetailDto {
    private  VariantWithAttributesDto variant;
    private  Integer quantity;
    private  Long price;
//
//    public GetImporterDetailDto(ImporterDetail importerDetail) {
//        this.variant = new GetVariantDto(importerDetail.getVariant());
//        this.quantity = importerDetail.getQuantity();
//        this.price = importerDetail.getPrice();
//    }
}
