package com.faqihdev.oa.shared.data.dto.response.order;


import com.faqihdev.oa.shared.data.statval.enumeration.ETransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransactionHistoryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date transactionTime;
    private List<ResponseOrderSalesItemDto> orderSalesItems;
    private String transactionId;
    private ETransactionType type;
    private Long userId;



}
