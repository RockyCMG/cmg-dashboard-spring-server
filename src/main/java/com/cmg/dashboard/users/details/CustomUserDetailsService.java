package com.cmg.dashboard.users.details;

import java.util.List;
import java.util.stream.Collectors;

import com.cmg.dashboard.users.User;
import com.cmg.dashboard.users.UserRegistrator;
import com.cmg.dashboard.users.UserRepository;
import com.cmg.dashboard.users.roles.Role;
import com.cmg.dashboard.users.roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cmg.dashboard.users.passwords.Password;
import com.cmg.dashboard.users.passwords.PasswordRepository;
import com.cmg.dashboard.users.userroles.UserRole;
import com.cmg.dashboard.users.userroles.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordRepository passwordRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findOneByEmail(email);
		Password password = passwordRepository.findOneByUserId(user);

		if (user == null) {
			throw new UsernameNotFoundException("Error: Email address not found: " + email);

		} else {
			List<UserRole> userRoles = userRoleRepository.findByUserId(user);
			List<String> userRolesList = userRoles
					.stream()
					.map(r -> r.getName().getName())
					.collect(Collectors.toList());

			return new CustomUserDetails(user, password, userRolesList);
		}
	}

	/**
	 * Register a new user.
	 *
	 * @param userRegistrator a UserRegistrator Object
	 */
	public void registerUser(UserRegistrator userRegistrator){
		User user = User.createUserFromRegistrator(userRegistrator);
		Password password = encryptPassword(new Password(user, userRegistrator.getPassword()));
		Role role = roleRepository.findOneByDisplayName(userRegistrator.getRole());
		UserRole userRole = new UserRole(user, role);

		saveUser(user);
		savePassword(password);
		saveUserRole(userRole);
	}

	/**
	 * Return a List<String> of all available Roles. The role's display names will be used to populate the list
	 *
	 * @return a List<String> of all available Roles. If no roles are found, an empty list will be returned
	 */
	public List<String> getAvailableRolesAsDisplayNames(){
		return roleRepository.findAll()
				.stream()
				.map(r -> r.getDisplayName())
				.collect(Collectors.toList());
	}

	private void saveUser(User user){
		userRepository.saveAndFlush(user);
	}

	private void savePassword(Password password){
		passwordRepository.saveAndFlush(password);
	}

	private void saveUserRole(UserRole userRole){
		userRoleRepository.saveAndFlush(userRole);
	}

	private Password encryptPassword(Password password){
		password.setPassword(passwordEncoder.encode(password.getPassword()));
		return password;
	}
}
