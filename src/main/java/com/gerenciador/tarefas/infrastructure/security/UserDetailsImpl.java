package com.gerenciador.tarefas.infrastructure.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.gerenciador.tarefas.domain.user.AppUser;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String displayName;

	public UserDetailsImpl(AppUser appUser) {
		this.username = appUser.getUsername();
		this.password = appUser.getPassword();
		this.displayName = appUser.getDisplayName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getDisplayName() {
		return displayName;
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
