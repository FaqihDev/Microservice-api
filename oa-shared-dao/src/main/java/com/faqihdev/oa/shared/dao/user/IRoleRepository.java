package com.faqihdev.oa.shared.dao.user;


import com.faqihdev.oa.shared.data.model.auth.Role;
import com.faqihdev.oa.shared.data.statval.enumeration.EUserRole;
import com.faqihdev.oa_util_core.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByUserRole(EUserRole userRole) throws DataNotFoundException;

    @Query("SELECT r FROM Role r JOIN User u where u.id = :userId")
    Set<Role> findRoleByUserId(Long userId) throws DataNotFoundException;

    @Query("SELECT p.name FROM Role r JOIN r.permissions p JOIN r.users u WHERE u.userName = :username")
    Set<String> findPermissionByUsername(String username);


}