package com.faqihdev.oa.shared.dao.menu;

import com.jamsirat.atmapi.model.config.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long> {
}
