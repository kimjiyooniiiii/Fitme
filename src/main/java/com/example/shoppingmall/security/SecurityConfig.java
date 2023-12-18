package com.example.shoppingmall.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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

    // 아이디, 패스워드 인증 후 반환
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
                        .loginPage("/loginForm")                    //인증이 필요한 URL에 접근하면 /loginForm 이동
                        .loginProcessingUrl("/tryLogin")            // 로그인 form action url과 일치
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/loginSuccess")             // 로그인 성공후 페이지
                        .failureUrl("/failureLogin")   // 로그인 실패후 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(HttpMethod.GET
                                ,"/join"
                                ,"/myBasket"
                                ,"/basket/add"
                                ,"/"
                                ,"/categoryProduct/**"
                                ,"/productDetail").permitAll()
                        .requestMatchers(HttpMethod.POST,"/checkDuplicatedId", "/send-sms","/basket/add").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/order/**","/join").hasAnyRole("USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**","/js/**","/error");
    }

}
