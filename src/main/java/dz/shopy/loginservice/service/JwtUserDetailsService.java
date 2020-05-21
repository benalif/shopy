package dz.shopy.loginservice.service;

import static dz.shopy.loginservice.exception.InvalidCredentialsException.BAD_CREDENTIALS;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dz.shopy.loginservice.entity.User;
import dz.shopy.loginservice.exception.InvalidCredentialsException;
import dz.shopy.loginservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (Objects.isNull(user)) {
			throw new InvalidCredentialsException(BAD_CREDENTIALS);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils
						.createAuthorityList(user.getRoles().stream().map(role -> role.getRoleName().toUpperCase())
								.collect(Collectors.toList()).stream().toArray(String[]::new)));

	}

}
