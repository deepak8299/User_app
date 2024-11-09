package com.User_app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private long id;	
	
	@NotEmpty
//	@Size(min= 5, message="username minimum 5 characters !! ")
	private String username;
	
	@Email(message = "Email Address is not valid !! ")
	private String userEmail;
	
	@NotEmpty
//	@Size(message="Password must be minimum 8 characters and maximum 16 characters !! ")
	private String userPassword;
	

}
