package com.cmg.dashboard.users.userroles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cmg.dashboard.users.User;
import com.cmg.dashboard.users.roles.Role;

@Entity
@Table(name = "userRoles")
public class UserRole {

	@Id
	@GeneratedValue
	private long userRoleId;

	@OneToOne
	@JoinColumn(name = "id")
	private User userId;

	@OneToOne
	@JoinColumn(name = "name")
	private Role name;

	public UserRole() {}

	public UserRole(User user, Role role){
		this.userId = user;
		this.name = role;
	}

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long id) {
		this.userRoleId = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}
}
