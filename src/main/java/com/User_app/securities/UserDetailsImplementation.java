
package com.User_app.securities;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.User_app.entity.User;
import com.User_app.exception.ResourceNotFoundException;
import com.User_app.repository.UserRepository;

@Service
public class UserDetailsImplementation implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email: " + username, 0));

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getUserPassword()).build();
	}
}
