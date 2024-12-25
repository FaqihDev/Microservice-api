package com.faqihdev.oa.shared.data.model.status;


import com.faqihdev.oa.shared.data.base.AAuditableBase;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "romantic_room", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class RomanticRoom extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "male_couple")
    private Participant maleCoupleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "female_couple")
    private Participant femaleCoupleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    private Progress progressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "principle_id")
    private Principle managedBy;

}