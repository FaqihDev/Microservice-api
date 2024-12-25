package com.faqihdev.oa.shared.data.dto.request.order;


import com.faqihdev.oa.shared.data.statval.enumeration.ETransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
    private Long userId;
    List<TransactionRequestProductDto> requestProduct;
    private ETransactionType type;

}

