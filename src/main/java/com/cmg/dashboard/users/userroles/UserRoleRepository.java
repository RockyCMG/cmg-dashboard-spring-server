package com.cmg.dashboard.users.userroles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmg.dashboard.users.User;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	List<UserRole> findByUserId(User user);
}
