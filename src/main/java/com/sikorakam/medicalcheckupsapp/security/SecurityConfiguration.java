package com.sikorakam.medicalcheckupsapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientDetailsServiceImpl clientDetailsServiceImpl;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }
    
    public SecurityConfiguration(ClientDetailsServiceImpl clientDetailsServiceImpl) {
        this.clientDetailsServiceImpl = clientDetailsServiceImpl;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().
                and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/public/users").hasRole("ADMIN")
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe().rememberMeParameter("checkRememberMe")
                .and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        WebMvcConfig webMvcConfig = new WebMvcConfig();
        daoAuthenticationProvider.setPasswordEncoder(webMvcConfig.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.clientDetailsServiceImpl);

        return daoAuthenticationProvider;
    }

}
