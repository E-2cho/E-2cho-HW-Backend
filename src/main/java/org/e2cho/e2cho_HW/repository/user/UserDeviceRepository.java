package org.e2cho.e2cho_HW.repository.user;

import org.e2cho.e2cho_HW.domain.user.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Long> {

    Optional<UserDevice> findByDeviceName(String deviceName);
}
