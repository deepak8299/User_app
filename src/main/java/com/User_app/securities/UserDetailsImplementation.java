
package com.User_app.securities;

import java.util.List;

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
    	
    	List<User> users = this.userRepository.findAll();
    	
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email: " + username, 0));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getUserPassword())
                .build();
    }
}




























//package com.User_app.securities;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.User_app.entity.User;
//import com.User_app.exception.ResourceNotFoundException;
//import com.User_app.repository.UserRepository;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
////import org.springframework.security.core.userdetails.User;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Service
//public class UserDetailsImplementation implements UserDetailsService{
//	
//	@Autowired
//	private UserRepository userRepository;
//	
////	private UserDetails userDetails;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// Loading userdetails from database by username
//		User user = this.userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "email : "+ username, 0));
//		UserDetails userDetails=null;
//		if(user != null) {
//			userDetails=org.springframework.security.core.userdetails.User.builder()
//			.username(user.getUsername())
//			.password(user.getUserPassword())
//////			.roles(user.getRoles().toArray(new String[0]))
//			.build();
//			
//			return userDetails;
//		}
//		else {
//			throw new UsernameNotFoundException(username);
//		}
//	}
//}
