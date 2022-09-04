package com.kadirirpik.security;

import com.kadirirpik.bean.PasswordEncoderMyBean;
import com.kadirirpik.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityMyConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoderMyBean passwordEncoderMyBean;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/**/favicon.ico", "/css/**", "/error/**", "/icons/**", "/images/**", "/js/**").permitAll()
            .antMatchers("/","/index", "/index.html", "/usersave", "/mail").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login",true)
                .permitAll()
            .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
            .and()
            .rememberMe()
                .key("re-me-key")
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("rememberlogin")
                .tokenValiditySeconds(60*60*24*7) // 7 gün
            .userDetailsService(userDetailsService);
        http.csrf().disable(); // h2 tarayıcıdan giris icin devre dışı bıraklıdı
        http.headers().frameOptions().disable(); // h2 tarayıcıdan giris icin devre dışı bıraklıdı
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProviderMyBean());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderMyBean(){
        DaoAuthenticationProvider authentication=new DaoAuthenticationProvider();
        authentication.setUserDetailsService(userService);
        authentication.setPasswordEncoder(passwordEncoderMyBean.passwordEncoder());
        return authentication;
    }
}
