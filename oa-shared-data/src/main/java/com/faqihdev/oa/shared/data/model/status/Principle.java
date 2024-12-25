package com.faqihdev.oa.shared.data.model.status;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.user.UserProfileExtended;
import com.faqihdev.oa.shared.data.statval.enumeration.EDapuanLevel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Principle", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Principle extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "dapuan_level")
    @Enumerated(EnumType.STRING)
    private EDapuanLevel dapuanLevel;


    @OneToMany(mappedBy = "managedBy",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval = true)
    List<RomanticRoom> romanticRoomId;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}