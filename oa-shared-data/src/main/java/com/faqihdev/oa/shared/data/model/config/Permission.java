package com.faqihdev.oa.shared.data.model.config;


import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.auth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Permission extends AAuditableBase implements Serializable {

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Menu> menus;
}
