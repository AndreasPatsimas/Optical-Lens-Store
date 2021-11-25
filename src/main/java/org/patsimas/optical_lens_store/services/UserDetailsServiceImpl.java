package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.domain.User;
import org.patsimas.optical_lens_store.dto.authenticate.MyUserDetails;
import org.patsimas.optical_lens_store.exceptions.ResourceNotFoundException;
import org.patsimas.optical_lens_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Load user process [username: {}] start", username);

		Optional<User> user = userRepository.findByUsername(username);

		if(user.isPresent()){
			log.info("Load user process completed");

			return new MyUserDetails(user.get());
		}
		else
			throw new ResourceNotFoundException("Δε βρέθηκε χρήστης με ονομασία: " + username);
	}
}