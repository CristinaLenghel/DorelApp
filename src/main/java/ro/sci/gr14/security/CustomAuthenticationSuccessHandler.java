package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * This class is used to handle a successful user authentication
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Slf4j
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * @param httpServletRequest the Spring MVC {@link HttpServletRequest}
     * @param httpServletResponse the Spring MVC {@link HttpServletResponse}
     * @param authentication the Java Security {@link Authentication}
     * @throws IOException produced by failed or interrupted I/O operations
     * @throws ServletException thrown by servlet when it encounters difficulty
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException{
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        log.info("onAuthenticationSuccess: roles:" + roles);
        if (roles.contains("ROLE_HANDYMAN")) {
            httpServletResponse.sendRedirect("/handyman");
        } else if (roles.contains("ROLE_CUSTOMER")) {
            httpServletResponse.sendRedirect("/customer");
        } else if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/home");
        }
    }
}