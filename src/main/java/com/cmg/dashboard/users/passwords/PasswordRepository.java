package com.cmg.dashboard.users.passwords;

import com.cmg.dashboard.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {

    Password findOneByUserId(User user);
}
