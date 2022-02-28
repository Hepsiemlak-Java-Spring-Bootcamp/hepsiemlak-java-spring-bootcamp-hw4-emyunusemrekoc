package main.java.emlakburada.service;

import emlakburada.dto.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.User;

public class UserBaseService {

	protected User convertToUserEntity(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setBiography(userRequest.getBiography());
		user.setUserType(userRequest.getUserType());
		return user;
	}

	protected UserResponse convertToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());
		userResponse.setBiography(user.getBiography());
		userResponse.setUserType(user.getUserType());
		return userResponse;
	}

}
