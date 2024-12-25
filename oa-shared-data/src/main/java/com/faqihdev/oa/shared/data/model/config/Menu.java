package com.faqihdev.oa.shared.data.model.config;



import com.faqihdev.oa.shared.data.base.AAuditableBase;
import com.faqihdev.oa.shared.data.model.auth.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu", schema = "private")
@Entity
@Builder
@Setter
@Getter
public class Menu extends AAuditableBase implements Serializable {

    @Column
    private String name;

    @Column
    private String url;

    @Column(name = "parent_id")
    private Long parentId;

    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @OneToMany
    private List<Menu> subMenus;

    @Column(name = "is_public")
    private boolean publicMenu;

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles;


    @ManyToMany
    @JoinTable(
            name = "menu_permission",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions;

}
