package com.faqihdev.oa.shared.data.model.user;

import com.faqihdev.oa.shared.data.base.AAuditableBase;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Education", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Education extends AAuditableBase implements Serializable {


    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_majors")
    private String schoolMajors;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_majors")
    private String universityMajors;

    @Column(name = "last_education")
    private String lastEducation;

    @Column(name = "on_going_education")
    private String onGoingEducation;

    @JoinColumn(name = "user_profile_extended_id")
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfileExtended userProfileExtendedId;

}