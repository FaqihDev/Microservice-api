package com.faqihdev.oa.shared.data.model.user;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.statval.enumeration.EJobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Job extends AAuditableBase implements Serializable {


    @Enumerated(EnumType.STRING)
    @Column(name = "job_status")
    private EJobStatus jobStatus;

    @Column(name = "office_address")
    private String office_address;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "profession")
    private String profession;

    @Column(name = "work_day")
    private String workDay;

    @Column(name = "work_time")
    private String workTime;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}