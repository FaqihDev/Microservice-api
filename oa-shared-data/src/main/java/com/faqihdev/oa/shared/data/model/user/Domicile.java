package com.faqihdev.oa.shared.data .model.user;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Domicile", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Domicile extends AAuditableBase implements Serializable {

    @Column(name = "kelompok_sambung")
    private String kelompokSambung;

    @Column(name = "desa_sambung")
    private String desaSambung;

    @Column(name = "kelompok_address")
    private String kelompokAddress;

    @Column(name = "desa_address")
    private String desaAddress;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}