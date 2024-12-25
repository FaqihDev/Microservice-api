package com.faqihdev.oa.shared.data.model.status;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.user.UserProfileExtended;
import com.faqihdev.oa.shared.data.statval.enumeration.EProgressStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Progress extends AAuditableBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private EProgressStatus status;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}