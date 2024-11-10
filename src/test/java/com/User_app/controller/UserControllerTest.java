package com.User_app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.User_app.payload.UserDto;
import com.User_app.service.UserService;

@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testCreateNewUser() throws Exception {
		UserDto userDto = new UserDto(1L, "John Doe", "john@example.com", "pass");
		when(userService.addUser(userDto)).thenReturn(userDto);

		mockMvc.perform(post("/api/users/addUser").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"password\":\"pass\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testDeleteUser() throws Exception {
		UserDto userDto = new UserDto(1L, "John Doe", "john@example.com", "pass");
		when(userService.deleteUser(1)).thenReturn(userDto);

		mockMvc.perform(delete("/api/users/1")).andExpect(status().isOk());
	}

	@Test
	public void testUpdateUser() throws Exception {
		UserDto userDto = new UserDto(1L, "John Doe", "john@example.com", "pass");
		when(userService.updateUser(userDto, 2)).thenReturn(userDto);

		mockMvc.perform(put("/api/users/2").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"password\":\"pass\"}")) // Adjust JSON
																											// as needed
				.andExpect(status().isOk());
	}

	@Test
	public void testGetUserById() throws Exception {
		UserDto userDto = new UserDto(1L, "John Doe", "john@example.com", "pass");
		when(userService.getByUserId(1)).thenReturn(userDto);

		mockMvc.perform(get("/api/users/1")).andExpect(status().isOk());

	}

	@Test
	public void testGetAllUsers() throws Exception {
		UserDto userDto = new UserDto(1L, "John Doe", "john@example.com", "pass");
		UserDto userDto2 = new UserDto(2L, "John2 Doen", "john2@example.com", "pass2");
		List<UserDto> users = Arrays.asList(userDto, userDto2);

		when(userService.getAllUser()).thenReturn(users);

		mockMvc.perform(get("/api/users/getAllUsers")).andExpect(status().isOk());

	}
}