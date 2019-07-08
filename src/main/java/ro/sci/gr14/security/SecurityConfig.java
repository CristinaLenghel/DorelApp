package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This class is used to provide web based security
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    /**
     * Controls the navigation to the subsequent destination
     *
     * @param authenticationSuccessHandler the Java Security {@link AuthenticationSuccessHandler}
     */
    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler){
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Provides default configuration to enable Spring HTTP Security
     *
     * @param http the Java Security {@link HttpSecurity}
     * @throws Exception in the event of an exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/contacts", "/test", "/error/**", "/jsondata/**", "/register/*", "/**/*.css", "/**/*.gif", "/**/js/**").access("permitAll()")
                .antMatchers("/handyman", "/handyman/**")
                .access("hasRole('HANDYMAN')")
                .antMatchers("/customer", "/customer/**")
                .access("hasRole('CUSTOMER')")
                .antMatchers("/admin", "/admin/**")
                .access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and().csrf().disable();
    }

    /**
     * Encoder for passwords with {@link BCryptPasswordEncoder} an implementation of the {@link PasswordEncoder} interface
     *
     * @return BCryptPasswordEncoder the Java Security {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder encoder( ){
        return new BCryptPasswordEncoder();
    }

    /**
     * Retrieves user details from a UserDetailsService
     *
     * @return authProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider( ){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    /**
     * Attempts to authenticate the passed Authentication object, returning a fully populated Authentication object
     *
     * @return authenticationManager a fully populated Authentication object
     * @throws Exception in the event of an exception
     */
    @Bean
    public AuthenticationManager customAuthenticationManager( ) throws Exception{
        return authenticationManager();
    }

    /**
     * Allows providing a parent AuthenticationManager that will be tried if this
     * AuthenticationManager was unable to attempt to authenticate the provided Authentication
     *
     * @param auth that should be used for retrial
     * @throws Exception in the event of an exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }
}
