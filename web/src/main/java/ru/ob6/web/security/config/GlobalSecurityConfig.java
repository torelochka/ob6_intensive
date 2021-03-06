package ru.ob6.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.ob6.web.security.handlers.FailedAuthHandler;
import ru.ob6.web.security.handlers.SuccessAuthHandler;

import javax.sql.DataSource;


@EnableWebSecurity
public class GlobalSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final SuccessAuthHandler successAuthHandler;

    private final FailedAuthHandler failedAuthHandler;

    private final UserDetailsService userDetailsService;

    private final DataSource dataSource;

    @Autowired
    public GlobalSecurityConfig(PasswordEncoder passwordEncoder, SuccessAuthHandler successAuthHandler, FailedAuthHandler failedAuthHandler, @Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.successAuthHandler = successAuthHandler;
        this.failedAuthHandler = failedAuthHandler;
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .ignoringAntMatchers("/api/**").and()
                .authorizeRequests()
                    .antMatchers("/films/search").permitAll()
                    .antMatchers("/profile", "/updateProfile").authenticated()
                    .antMatchers("/booking/**").hasAuthority("ROLE_USER")
                    .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").and()
                .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/profile")
                    .successHandler(successAuthHandler)
                    .failureHandler(failedAuthHandler).and()
                    //.failureUrl("/signIn?error").and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/signIn")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
    }

    //TODO openId

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
