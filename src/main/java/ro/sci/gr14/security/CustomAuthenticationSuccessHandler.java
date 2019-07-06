package ro.sci.gr14.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.Set;

@Slf4j
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        log.info("onAuthenticationSuccess: roles:"+roles);
        if (roles.contains("ROLE_HANDYMAN")) {
            httpServletResponse.sendRedirect("/handyman");
        } else if (roles.contains("ROLE_CUSTOMER")) {
            httpServletResponse.sendRedirect("/customer");
        } else if (roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        } else{
            httpServletResponse.sendRedirect("/home");
        }
    }
}