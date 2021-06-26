package ru.ob6.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.ob6.web.security.handlers.SuccessAuthHandler;


@EnableWebSecurity
public class GlobalSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SuccessAuthHandler successAuthHandler;

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").and()
                .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/profile")
                    .successHandler(successAuthHandler)
                    .failureUrl("/signIn?error").and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/signIn")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
    }

    //TODO openId, запомнить меня

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
