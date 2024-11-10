package com.User_app.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.User_app.entity.User;
import com.User_app.payload.UserDto;
import com.User_app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplementTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private UserServiceImplement userService;

	private User user;
	private UserDto userDto;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setId(1L);
		user.setUsername("testuser");
		user.setUserEmail("test@example.com");
		user.setUserPassword("password");

		userDto = new UserDto();
		userDto.setId(1L);
		userDto.setUsername("testuser");
		userDto.setUserEmail("test@example.com");
		userDto.setUserPassword("password");
	}

	@Test
	public void testAddUser() {
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

		UserDto savedUserDto = userService.addUser(userDto);

		assertNotNull(savedUserDto);
		assertEquals(savedUserDto.getUsername(), "testuser");
		verify(userRepository).save(any(User.class));
	}

	@Test
	public void testUpdateUser() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

		UserDto updatedUserDto = userService.updateUser(userDto, 1L);

		assertNotNull(updatedUserDto);
		assertEquals(updatedUserDto.getUsername(), "testuser");
		verify(userRepository).findById(1L);
	}

	@Test
	public void testDeleteUser() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

		UserDto deletedUserDto = userService.deleteUser(1L);

		assertNotNull(deletedUserDto);
		assertEquals(deletedUserDto.getUsername(), "testuser");
		verify(userRepository).delete(any(User.class));
	}

	@Test
	public void testGetByUserId() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

		UserDto foundUserDto = userService.getByUserId(1L);

		assertNotNull(foundUserDto);
		assertEquals(foundUserDto.getUsername(), "testuser");
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(user);

		when(userRepository.findAll()).thenReturn(users);
		when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

		List<UserDto> allUsers = userService.getAllUser();

		assertNotNull(allUsers);
		assertEquals(allUsers.size(), 1);
	}
}