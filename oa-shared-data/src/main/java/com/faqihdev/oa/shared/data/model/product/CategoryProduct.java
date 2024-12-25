package com.faqihdev.oa.shared.data.model.product;


import com.faqihdev.oa.shared.data.base.AAuditableBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "category_product")
public class CategoryProduct extends AAuditableBase {


    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description_category")
    private String descriptionCategory;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;


}