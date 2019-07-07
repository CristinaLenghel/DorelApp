package ro.sci.gr14.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web
        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/home","/test","/error/**","/jsondata/**","/register/*","/**/*.css", "/**/*.gif" ,"/**/js/**").access("permitAll()")
                .antMatchers("/handyman","/handyman/**")
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

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
