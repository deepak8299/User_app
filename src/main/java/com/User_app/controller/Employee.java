package com.User_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.User_app.payload.UserDto;
import com.User_app.service.UserService;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/employees")
public class Employee {
	
	@Autowired
	private UserService userService;
	
	
	// add new User
	@PostMapping("/add")
	public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto){
		UserDto savedUserDto = this.userService.addUser(userDto);
		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable long userId){
		UserDto deletedUserDto = this.userService.deleteUser(userId);
		return new ResponseEntity<>(deletedUserDto, HttpStatus.OK);
	}
	
	//update user details
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable long userId){
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
		UserDto userDto = this.userService.getByUserId(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> userDtos = this.userService.getAllUser();
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}
}
