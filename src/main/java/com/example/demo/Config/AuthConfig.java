package com.example.demo.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
                .and()
                .formLogin()
                .and().exceptionHandling().accessDeniedPage("/accessdenied");

        // disable frame options
        http.headers().frameOptions().disable();;
        //disable this line to reproduce the CORS 401
        http.cors();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery("select ID, AUTHORITY from "
                + "AUTHORITIES  where ID=?")
                .usersByUsernameQuery("select ID,Password,1 as enabled from USERS where ID=?");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}



