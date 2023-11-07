package com.example.shoppingmall.config;

import jakarta.servlet.http.HttpSessionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
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
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.time.LocalDateTime;

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

    // 세션 정보 확인
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher(){
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher(){
            @Override
            public void sessionCreated(HttpSessionEvent event) {
                super.sessionCreated(event);
                System.out.printf("====> [%s] 세션 생성됨 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent event) {
                super.sessionDestroyed(event);
                System.out.printf("====> [%s] 세션 만료됨 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
                super.sessionIdChanged(event, oldSessionId);
                System.out.printf("====> [%s] 세션 아이디 변경 %s \n" , LocalDateTime.now(), event.getSession().getId());
            }
        });
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
                                ,"/myBasket"
                                ,"/basket/add"
                                ,"/"
                                ,"/categoryProduct/**"
                                ,"/productDetail"
                                ,"/css/**"
                                ,"/js/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/checkDuplicatedId", "/send-sms","/basket/add").permitAll()
                        .requestMatchers(HttpMethod.GET, "/order/**","/join").hasAnyRole("USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
