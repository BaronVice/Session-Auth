package admin_user.service;

import admin_user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin_user.dto.UserDto;
import admin_user.model.User;
import admin_user.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Override
	public User save(UserDto userDto) {

		User user = new User(
				userDto.getUsername(),
				passwordEncoder.encode(userDto.getPassword()) ,
				Role.USER
		);

		return userRepository.save(user);
	}

}
