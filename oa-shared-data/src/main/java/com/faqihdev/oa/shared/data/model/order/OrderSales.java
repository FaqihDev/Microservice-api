package com.faqihdev.oa.shared.data.model.order;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.auth.User;
import com.faqihdev.oa.shared.data.statval.enumeration.ETransactionType;
import jakarta.persistence.*;
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
@Table(name = "order_sales")
public class OrderSales extends AAuditableBase {

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private ETransactionType transactionType;

    @OneToMany(mappedBy = "orderSales",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderSalesItem> orderSalesItem;

    @Column(name = "public_id")
    private String publicId;

}
