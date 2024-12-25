package com.faqihdev.oa.shared.dao.model.user;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.user.UserProfileExtended;
import com.faqihdev.oa.shared.data.statval.enumeration.EAgeCriteria;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "preference", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Preference extends AAuditableBase implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "age_criteria")
    private EAgeCriteria ageCriteria;

    @Column(name = "specific_criteria")
    private String specificCriteria;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "couple_job_criteria")
    private String jobCriteria;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}