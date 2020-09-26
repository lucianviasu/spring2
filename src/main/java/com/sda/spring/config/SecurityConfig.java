package com.sda.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        ///////////////////////////////////////Roles based users////////////////////////////////////////////////////////////
        auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password(passwordEncoder().encode("pass1"))
                    .roles("ADMIN", "USER")
                .and()
                    .withUser("user")
                    .password(passwordEncoder().encode("pass"))
                    .roles("USER");

////////////////////////////////////Authorities based users////////////////////////////////////////////////////////////
//        auth.inMemoryAuthentication()
//                    .withUser("admin")
//                    .password(passwordEncoder().encode("pass1"))
//                    .authorities("READ", "WRITE")
//                .and()
//                    .withUser("user")
//                    .password(passwordEncoder().encode("pass"))
//                    .authorities("READ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
/////////////////////////////////////////Role based matchers////////////////////////////////////////////////////////////
//                    .antMatchers("/api/v1/companies/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()

////////////////////////////////////////Permit all/////////////////////////////////////////////////////////////////////
                    .anyRequest().permitAll()
//                    -> permite toate requesturile(autentificate sau ne-autentificate)

////////////////////////////////////////Authorities based matchers/////////////////////////////////////////////////////
//                    .antMatchers("/api/v1/companies/create").hasAuthority("WRITE")
//                    .anyRequest().authenticated()
                .and()
                    .httpBasic();

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
