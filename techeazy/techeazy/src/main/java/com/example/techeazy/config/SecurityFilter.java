package com.example.techeazy.config;

import com.example.techeazy.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
    @EnableWebSecurity
    public class SecurityFilter  {
        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private jwtAuthenticationFilter jwtauthenticationfilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
            http
                    .csrf(csrfConfig->csrfConfig.disable())
                    .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(authConfig->{
                        authConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                        authConfig.requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
                        authConfig.requestMatchers("/error").permitAll();

                        authConfig.requestMatchers(HttpMethod.POST,"/Students/addStudent").hasAuthority(Permission.SAVE_STUDENT.name());
                        authConfig.requestMatchers(HttpMethod.POST,"/Subject/addSubject").hasAuthority(Permission.SAVE_SUBJECT.name());
                        authConfig.requestMatchers(HttpMethod.POST,"/Students/enroll").hasAuthority(Permission.ENROLL_STUDENT.name());
                        authConfig.requestMatchers(HttpMethod.GET,"/Subject/getAllSubjects").hasAuthority(Permission.GET_ALL_SUBJECTS.name());
                        authConfig.requestMatchers(HttpMethod.GET,"/Student/getAllStudents").hasAuthority(Permission.GET_ALL_STUDENTS.name());
                        authConfig.anyRequest().denyAll();

                    });
            return http.build();
        }




    }

