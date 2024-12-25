package com.faqihdev.oa.shared.data.model.user;

import com.faqihdev.oa.shared.data.base.AAuditableBase;

import com.faqihdev.oa.shared.data.statval.enumeration.EDapuanLevel;
import com.faqihdev.oa.shared.data.statval.enumeration.EStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Status", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Status extends AAuditableBase implements Serializable {


    @Column(name = "dapuan")
    private String dapuan;

    @Enumerated(EnumType.STRING)
    @Column(name = "dapuan_level")
    private EDapuanLevel dapuanLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EStatus status;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}