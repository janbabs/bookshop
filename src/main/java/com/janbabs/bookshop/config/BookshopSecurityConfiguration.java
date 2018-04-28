package com.janbabs.bookshop.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class BookshopSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/books/add").hasAuthority("ADMIN")
                .antMatchers("/books/delete/**").hasAuthority("ADMIN")
                .antMatchers("/user/add").permitAll()
                .antMatchers("/user/**").hasAuthority("ADMIN")
                .antMatchers("/order/add/**").hasAuthority("USER")
                .antMatchers("/order/change/**").hasAuthority("ADMIN")
                .antMatchers("/order/all").hasAnyAuthority("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").defaultSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().logoutUrl("logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    @Query
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login,password,'true' from user where login=? ")
                .authoritiesByUsernameQuery("select login,USER_TYPE  from user where login=?").passwordEncoder(new BCryptPasswordEncoder(10));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder;
    }
}