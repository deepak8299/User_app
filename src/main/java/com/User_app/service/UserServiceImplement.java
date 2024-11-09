package com.User_app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.User_app.entity.User;
import com.User_app.exception.ResourceNotFoundException;
import com.User_app.payload.UserDto;
import com.User_app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserServiceImplement implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setUserPassword(PasswordEncoder.encode(user.getUserPassword()));
		User savedUser = this.userRepository.save(user);
		UserDto savedUserDto = this.modelMapper.map(savedUser, UserDto.class);
		return savedUserDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setUsername(userDto.getUsername());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		UserDto updatedUserDto = this.modelMapper.map(user, UserDto.class);
		return updatedUserDto;
	}

	@Override
	public UserDto deleteUser(long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
		UserDto deletedUserDto = this.modelMapper.map(user, UserDto.class);
		return deletedUserDto;
		
	}

	@Override
	public UserDto getByUserId(long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		UserDto getUserDto = this.modelMapper.map(user, UserDto.class);
		return getUserDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listOfUsers = this.userRepository.findAll();
		List<UserDto> listOfUserDtos = listOfUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return listOfUserDtos;
	}

}
