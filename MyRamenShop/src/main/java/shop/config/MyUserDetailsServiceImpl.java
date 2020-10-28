package shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.entity.Membership;
import shop.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Membership membership = userRepository.findByUsername(username);
		  if (membership == null) {
	            throw new UsernameNotFoundException(username + " not found");
	        }
		  UserDetails userDetails = User.builder()
	                .username(membership.getUsername())
	                .password("{noop}" + membership.getPassword())
	                .roles("User").build();
		return userDetails;
	}

}
