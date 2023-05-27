package com.luv2code.springbootlibrary.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luv2code.springbootlibrary.entity.AppUser;
import com.luv2code.springbootlibrary.exception.UserInactiveException;
import com.luv2code.springbootlibrary.service.AppUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserService appUserService;

	public UserDetailsServiceImpl(AppUserService appUserService) {
		super();
		this.appUserService = appUserService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		AppUser user = appUserService.getUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

		if(!user.getIsActive()) {
			throw new UserInactiveException(String.format("User %s is inactive, please contact our support team.", username));  
		}
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
		return mapUserToCustomUserDetails(user, authorities);
	}

	private CustomUserDetails mapUserToCustomUserDetails(AppUser appUser, List<SimpleGrantedAuthority> authorities) {
		CustomUserDetails customUserDetails = new CustomUserDetails();

		if (appUser.getUser()!=null) {
			customUserDetails.setId(appUser.getUser().getUserId());
			customUserDetails.setIsVerified(appUser.getUser().getIsVerified());
		} else if (appUser.getAdmin()!=null) {
			customUserDetails.setId(appUser.getAdmin().getAdminId());
		}

		customUserDetails.setUsername(appUser.getUsername());
		customUserDetails.setPassword(appUser.getPassword());

		if (appUser.getUser()!=null) {
			customUserDetails.setName(appUser.getUser().getfName());
			customUserDetails.setEmail(appUser.getUser().getEmail());
		} else if (appUser.getAdmin()!=null) {
			customUserDetails.setName(appUser.getAdmin().getfName());
			customUserDetails.setEmail(appUser.getAdmin().getEmail());
		}

		customUserDetails.setAuthorities(authorities);

		return customUserDetails;
	}
}
