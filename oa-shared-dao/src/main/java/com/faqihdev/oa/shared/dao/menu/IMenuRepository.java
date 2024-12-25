package com.faqihdev.oa.shared.dao.menu;

import com.jamsirat.atmapi.model.config.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMenuRepository extends JpaRepository<Menu,Long> {

    @Query("SELECT m FROM Menu m JOIN m.roles r where r.userRole IN:userRoleNames")
    List<Menu> findAllByRoles_NamesIn(List<String> userRoleNames);

    Menu findByUrl(String url);

}
