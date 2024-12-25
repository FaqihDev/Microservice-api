package com.faqihdev.oa.shared.data.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDeleteProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}

