package com.janbabs.bookshop.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class BookshopSecurityConfiguration extends WebSecurityConfigurerAdapter{
//
    private final DataSource dataSource;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().and().csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//        httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2/**").permitAll()
//                .and()
//                .formLogin().loginPage("/login")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .and()
//                .logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/");
//    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").defaultSuccessUrl("/")
//                .and()
//                .logout().logoutUrl("logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/");
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/books/add").hasAuthority("ADMIN")
                .antMatchers("/books/delete/**").hasAuthority("ADMIN")
                .antMatchers("/user/add").permitAll()
                .antMatchers("/user/**").hasAuthority("ADMIN")
                .and()
                .formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login,password,'true' from user where login=? ")
                .authoritiesByUsernameQuery("select login,USER_TYPE  from user where login=?").passwordEncoder(new BCryptPasswordEncoder(10));
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder;
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select login,password,'true' from user where login=? ")
//                .authoritiesByUsernameQuery("select login,TYPE_USER  from user where login=?").passwordEncoder(new BCryptPasswordEncoder(10));
//    }
}