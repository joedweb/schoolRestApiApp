package com.binary.schoolClassAdministration.security;

import com.binary.schoolClassAdministration.exception.AuthEntryPoint;
import com.binary.schoolClassAdministration.repository.UserRepository;
import com.binary.schoolClassAdministration.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//-----------------------SPRING SECURITY------------------------------
@Configuration              //because class will have multiple types of objects (all different methods coming from the methods)
@EnableWebSecurity      //removes basics demo config to only use yours
public class SecurityConfig {

    // for JWT
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private com.binary.schoolClassAdministration.security.AuthenticationFilter authenticationFilter;          //the filter we made

    @Autowired
    private AuthEntryPoint authEntryPoint;
    //


    @Bean               //so we can inject when needed
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    //all filters will be here!
        //Everyone should be able to Get
        return http
                .cors(Customizer.withDefaults())
                .csrf(c -> c.disable())                                         //  disabling csrf for security
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST,"/login").permitAll()              //Jwt
                                .anyRequest()       //"any other-
                                .authenticated())  // -needs authentication" //jwt

                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((ex)->ex.authenticationEntryPoint(authEntryPoint) )//jwt

                .build();                                                       // build this bean
    }
    //csrf: Cross site request forgery(CSRF) attack
    //cors

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){           //to encode the passwords
        return new BCryptPasswordEncoder();
    }


    //needed for Spring Security (below). Not for JWT

//    @Bean
//    public UserDetailsService userDetailsService(){             //Generate the users details
//        //admin
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(bCryptPasswordEncoder().encode( "adminPass"))      //passwords are on plain text, so we need to encode them
//                .roles("ADMIN")
//                .build();
//
//        //user
//        var user = User.builder()       //same as:    UserDetails user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);          //saving here, not as an entity or database
//    }



    // For JWT
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig) throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }


}
