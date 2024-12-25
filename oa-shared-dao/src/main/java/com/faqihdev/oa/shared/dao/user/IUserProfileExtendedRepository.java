package com.faqihdev.oa.shared.dao.user;


import com.faqihdev.oa.shared.data.model.user.UserProfile;
import com.faqihdev.oa.shared.data.model.user.UserProfileExtended;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserProfileExtendedRepository extends JpaRepository<UserProfileExtended,Long> {

    Optional<UserProfileExtended> findByUserProfile (Optional<UserProfile> userProfile);
}