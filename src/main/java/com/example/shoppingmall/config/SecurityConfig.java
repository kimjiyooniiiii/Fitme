package com.example.shoppingmall.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 아이디, 패스워드 인증 관리
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception{
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService());
        dao.setPasswordEncoder(passwordEncoder());

        return dao;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin
                        .loginPage("/")        //인증이 필요한 URL에 접근하면 /login 이동
                        .loginProcessingUrl("/login")    // form action url
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/loginSuccess")     // 로그인 성공후 페이지
                        .failureUrl("/")   // 로그인 실패후 페이지
                        .permitAll()
                        /*.successHandler()
                        .failureHandler()*/
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(HttpMethod.GET
                                ,"/join"
                                ,"/"
                                ,"/categoryProduct/**"
                                /*,"/productDetail"*/
                                ,"/css/**"
                                ,"/js/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/checkDuplicatedId", "/join", "/send-sms").permitAll()
                        .requestMatchers(HttpMethod.GET, "/order/**").hasAnyRole("USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
