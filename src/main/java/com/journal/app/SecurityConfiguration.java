//package com.journal.app;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("ajay").password("test").roles("USER")
//                .and()
//                .withUser("demo").password("demo").roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/", "/login", "/sign-up")
//                .permitAll()
//                .anyRequest()
//                .fullyAuthenticated()
//                .and()
//                //.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
//                .httpBasic();
//
//    }
//
//    @Bean
//    public CustomFilter customFilter() {
//        return new CustomFilter();
//    }
//}