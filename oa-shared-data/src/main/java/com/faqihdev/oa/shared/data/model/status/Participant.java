package com.faqihdev.oa.shared.data.model.status;


import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.user.UserProfileExtended;
import com.faqihdev.oa.shared.data.statval.enumeration.EGender;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Participant", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Participant extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "chosen_by")
    private Long chosenBy;

    @Column(name = "choose_to")
    private Long chooseTo;

    @Column(name = "is_taken")
    private Boolean isTaken;

    @Column(name = "managed_by")
    private Long managedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private EGender gender;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}