package com.example.WebSpringStudy.config.auth;

import com.example.WebSpringStudy.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 기능 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 화면을 사용하기 위한 disable
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    //열람 권한이 있는 url 지정
                    .authorizeRequests()
                        .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profile")
                    .permitAll()
                        //api/v1/**의 api는 USER권한을 가진 사람만 사용 가능
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                            .anyRequest().authenticated()
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
