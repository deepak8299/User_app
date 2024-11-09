
package com.User_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.User_app.payload.UserDto;
import com.User_app.securities.JwtHelper;
import com.User_app.securities.JwtRequest;
import com.User_app.securities.JwtResponse;
import com.User_app.securities.UserDetailsImplementation;
import com.User_app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Autowired
    private UserDetailsImplementation userDetailsImplementation;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwthelper;
    
    @Autowired
	private UserService userService;
    
    
    // Create new User
 	@PostMapping("/signup")
 	public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
 		UserDto savedUserDto = this.userService.addUser(userDto);
 		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
 	}

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getUserName(), jwtRequest.getPassword());

        UserDetails userDetails = userDetailsImplementation.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwthelper.generateToken(userDetails);
        System.out.println("JWT Token: " + token); 

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .userName(userDetails.getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            this.authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Invalid Username or Password !!", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}































//package com.User_app.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.User_app.securities.JwtHelper;
//import com.User_app.securities.JwtRequest;
//import com.User_app.securities.JwtResponse;
//import com.User_app.securities.UserDetailsImplementation;
//
//@RestController
//@RequestMapping("/auth")
//public class JwtAuthenticationController {
//
//    @Autowired
//    private UserDetailsImplementation userDetailsImplementation;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtHelper jwthelper;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
//
//        this.doAuthenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
//
//        UserDetails userDetails = userDetailsImplementation.loadUserByUsername(jwtRequest.getUserName());
//        String token = this.jwthelper.generateToken(userDetails);
//
//        JwtResponse response = JwtResponse.builder()
//                .token(token)  // Corrected method name to match field name
//                .userName(userDetails.getUsername())  // Corrected method name to match field name
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    private void doAuthenticate(String userName, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
//        try {
//            this.authenticationManager.authenticate(authentication);
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Invalid Username or Password !!");
//        }
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }
//}
