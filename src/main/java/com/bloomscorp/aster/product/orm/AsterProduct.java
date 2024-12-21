package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.contract.AsterProductContract;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProduct<
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    SCAM extends AsterProductSubCategoryMapping<
        CA,
        SCA,
        CO,
        ? extends AsterProduct<CA, SCA, CO, ?, ?>
        >,
    COM extends AsterProductCollectionMapping<
        CA,
        SCA,
        CO,
        ? extends AsterProduct<CA, SCA, CO, ?, ?>
        >
    > extends BehemothORM {

    @Column(
        name = AsterProductContract.NAME,
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = AsterProductContract.SLUG,
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String slug;

    @Column(
        name = AsterProductContract.SKU,
        nullable = false,
        columnDefinition = "VARCHAR",
        length = 25
    )
    private String sku;

    @Column(
        name = AsterProductContract.PRICE,
        nullable = false,
        columnDefinition = "NUMERIC"
    )
    @ColumnDefault("0.00")
    private Double price = 0.00;

    @Column(
        name = AsterProductContract.QUANTITY,
        nullable = false,
        columnDefinition = "NUMERIC"
    )
    @ColumnDefault("0.00")
    private Double quantity = 0.00;

    @Column(
        name = AsterProductContract.DESCRIPTION,
        nullable = false,
        columnDefinition = "TEXT"
    )
    @FullTextField
    private String description;

    @Column(
        name = AsterProductContract.DETAILS,
        nullable = false,
        columnDefinition = "TEXT"
    )
    @FullTextField
    private String details;

    @Column(
        name = AsterProductContract.SALE,
        columnDefinition = "BOOLEAN"
    )
    @ColumnDefault("FALSE")
    private boolean sale = false;

    @Column(
        name = AsterProductContract.DISCOUNT,
        columnDefinition = "NUMERIC"
    )
    @ColumnDefault("0.00")
    private double discount = 0.00;

    @Column(
        name = AsterProductContract.DISABLED,
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    @ColumnDefault("false")
    private boolean disabled = false;
    
    @Column(
        name = AsterProductContract.PRODUCT_RANK,
        nullable = false,
        columnDefinition = "BIGINT"
    )
    private Long productRank = 0L;

    public abstract CA getCategory();

    public abstract void setCategory(CA category);

    public abstract List<SCAM> getSubCategoryMappings();

    public abstract void setSubCategoryMappings(List<SCAM> subCategories);

    public abstract List<COM> getCollectionMappings();

    public abstract void setCollectionMappings(List<COM> collections);
}
