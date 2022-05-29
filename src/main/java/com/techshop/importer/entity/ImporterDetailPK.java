package com.techshop.importer.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ImporterDetailPK implements Serializable {
    @Column(name = "import_id")
    private Long importId;

    @Column(name = "variant_id")
    private Long variantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImporterDetailPK that = (ImporterDetailPK) o;
        return importId.equals(that.importId) && variantId.equals(that.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importId, variantId);
    }
}
