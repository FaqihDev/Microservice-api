package com.faqihdev.oa.shared.data.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeleteProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}

