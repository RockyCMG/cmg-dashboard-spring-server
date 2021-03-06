package com.cmg.dashboard.users.details;

import com.cmg.dashboard.users.User;
import com.cmg.dashboard.users.passwords.Password;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private User user;
    private Password password;
    private List<String> roles;

    public CustomUserDetails(User user, Password password, List<String> roles){
        this.user = user;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String rolesString = StringUtils.collectionToCommaDelimitedString(roles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(rolesString);
    }

    @Override
    public String getPassword() {
        return password.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
