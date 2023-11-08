package com.example.shoppingmall.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FailureLogin extends SimpleUrlAuthenticationFailureHandler {
   /* @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl("/failureLogin?msg=로그인실패");
        super.onAuthenticationFailure(request, response, exception);
    }*/
}