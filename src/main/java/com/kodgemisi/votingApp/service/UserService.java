package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozge on 29.08.2016.
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = getUserByEmail(email);
		List<SimpleGrantedAuthority> auth = (List<SimpleGrantedAuthority>) user.getAuthorities();

		if (null == user) {
			throw new UsernameNotFoundException("User with username: " + email + " not found.");
		} else {
			return user;
		}
	}
}
