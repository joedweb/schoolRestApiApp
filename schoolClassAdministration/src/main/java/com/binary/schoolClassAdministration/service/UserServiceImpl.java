package com.binary.schoolClassAdministration.service;

import com.binary.schoolClassAdministration.Entity.User;
import com.binary.schoolClassAdministration.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {
    //we do not have a UserService because UserDetailsService is an interface that has it already. We juts need to bring our implmenetation

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        Optional<User> user = userRepository.findUserByUsername(username);

        if(user.isPresent()){
            User currentUser = user.get();
            //it's from user details so we need to build it (as seen on Security/securityConfig)
            builder = org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User
                    .withUsername(username);

            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());

        }else{
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
