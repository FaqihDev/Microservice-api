package com.faqihdev.oa.shared.data.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAddProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String productName;
    private String descriptionProduct;
    private String image;
    private String productCategoryName;
    private Integer stockProduct;
}

