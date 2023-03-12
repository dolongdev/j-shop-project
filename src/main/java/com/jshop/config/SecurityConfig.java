package com.jshop.config;


import com.jshop.dto.AccountDto;
import com.jshop.model.Account;
import com.jshop.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    ModelMapper modelMapper;

    //Cung cap nguon du lieu dang nhap
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account account = accountService.getAccount(username);
                String password = pe.encode(account.getPassword());
                String[] roles = account.getAuthorities().stream()
                        .map(er -> er.getRole().getRoleId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + "not Found");
            }
        });
    }

    // Phan quyen su dung
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/administration/**").hasRole("AD")
                .antMatchers("/api/**").permitAll()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/home", false)
                .failureUrl("/security/login");

        http.rememberMe().tokenValiditySeconds(86400);

        http.exceptionHandling().accessDeniedPage("/security/unauthoried");

        http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/home");

        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            request.getSession().setAttribute("errorMessage"
                    , "Bạn không có quyền truy cập trang này!");
            response.sendRedirect("/security/login?error=true");
        });
    }

    // Ma hoa mat khau
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Cho phep truy xuat Rest API ben ngoai Domain khac

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}