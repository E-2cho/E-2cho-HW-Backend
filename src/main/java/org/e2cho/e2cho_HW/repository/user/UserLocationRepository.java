package org.e2cho.e2cho_HW.repository.user;

import org.e2cho.e2cho_HW.domain.user.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

    UserLocation findByUserId(Long userId);
}
