package com.faqihdev.oa.shared.dao.user;

import com.faqihdev.oa.shared.data.model.auth.User;
import com.faqihdev.oa.shared.data.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    @Query("SELECT up FROM UserProfile up where up.user.id = :pUserId")
    Optional <UserProfile> findByUserId(Long pUserId);

    Optional <UserProfile> getUserProfileByUser(User user);

}