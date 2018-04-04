package com.rest.rest.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder, userDetailsService: UserDetailsService) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/resources/**", "/error")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
    }
}