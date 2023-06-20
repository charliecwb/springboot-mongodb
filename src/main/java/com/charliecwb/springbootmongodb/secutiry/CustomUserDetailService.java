package com.charliecwb.springbootmongodb.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLoginUserName(username);

        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
            		.username(user.getLogin().getUserName())
            		.password(user.getLogin().getPassword())
            		.roles("USER")
            		.build();
                    
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
