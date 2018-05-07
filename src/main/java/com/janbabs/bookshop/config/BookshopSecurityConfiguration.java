package com.janbabs.bookshop.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class BookshopSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/books/add").hasAuthority("ADMIN")
                .antMatchers("/books/delete/**").hasAuthority("ADMIN")
                .antMatchers("/books/change/**").hasAuthority("ADMIN")
                .antMatchers("/user/add").anonymous()
                .antMatchers("/user/**").hasAuthority("ADMIN")
                .antMatchers("/order/add/**").hasAuthority("USER")
                .antMatchers("/order/change/**").hasAuthority("ADMIN")
                .antMatchers("/order/all").authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("login")
                .passwordParameter("password").defaultSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
    }

    @Override
    @Query
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login,password,'true' from user where login=? ")
                .authoritiesByUsernameQuery("select login,USER_TYPE  from user where login=?")
                .passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

