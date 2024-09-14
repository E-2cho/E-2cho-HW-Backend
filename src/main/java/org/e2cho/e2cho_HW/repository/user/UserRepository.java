package org.e2cho.e2cho_HW.repository.user;

import org.e2cho.e2cho_HW.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
