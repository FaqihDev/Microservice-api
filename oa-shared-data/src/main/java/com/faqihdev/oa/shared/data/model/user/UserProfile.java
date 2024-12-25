package com.faqihdev.oa.shared.data.model.user;

import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "user_profile", schema = "private")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = -4474084425849609899L;


    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "is_mobile_phone_number_verified")
    private Boolean isMobilePhoneNumberVerified;

    @Column(name = "is_verified_user")
    private Boolean isVerifiedUser;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

}