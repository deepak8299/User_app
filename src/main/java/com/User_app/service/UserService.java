package com.User_app.service;

import java.util.List;

import com.User_app.payload.UserDto;

public interface UserService {
	
	//add
	public UserDto addUser(UserDto userDto);
	
	//update
	public UserDto updateUser(UserDto userDto, long userId);
	
	//delete
	public UserDto deleteUser(long userId);
	
	//getbyid
	public UserDto getByUserId(long userId);
	
	//getall
	public List<UserDto> getAllUser();

}
