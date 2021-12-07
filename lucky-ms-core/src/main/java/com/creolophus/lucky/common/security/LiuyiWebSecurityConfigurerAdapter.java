package com.creolophus.lucky.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author magicnana
 * @date 2019/7/2 上午11:54
 */
public class LiuyiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;
  private AccessDeniedHandler accessDeniedHandler;
  private AuthenticationEntryPoint authenticationEntryPoint;
  private JwtAuthenticationTokenFilter authenticationTokenFilter;

  public LiuyiWebSecurityConfigurerAdapter(
      UserDetailsService userDetailsService,
      AccessDeniedHandler accessDeniedHandler,
      AuthenticationEntryPoint authenticationEntryPoint,
      JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
    this.userDetailsService = userDetailsService;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.authenticationTokenFilter = jwtAuthenticationTokenFilter;
  }

  @Autowired(required = false)
  public WebSecurityAppender webSecurityAppender;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity web) {

    web.ignoring().antMatchers("/actuator/**");

    web.ignoring().antMatchers("/error");

    web.ignoring().antMatchers(HttpMethod.OPTIONS);

    web.ignoring()
        .antMatchers(
            "/",
            "/doc.html",
            "/csrf",
            "/webjars/**",
            "swagger-ui.html",
            "**/swagger-ui.html",
            "/favicon.ico",
            "/**/*.css",
            "/**/*.js",
            "/**/*.png",
            "/**/*.gif",
            "/swagger-resources/**",
            "/v2/**",
            "/v3/**",
            "/**/*.ttf");
    web.ignoring()
        .antMatchers(
            "/swagger-resources/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html");

    if(webSecurityAppender!=null){
      webSecurityAppender.configure(web);
    }
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //        authSetting.getNoAuthenticationPatterns().add("/error/**");
    //
    //        String[] array = new String[authSetting.getNoAuthenticationPatterns().size()];
    //        for(int i=0;i<authSetting.getNoAuthenticationPatterns().size();i++){
    //            String up = authSetting.getNoAuthenticationPatterns().get(i);
    //            array[i] = up;
    //        }

    httpSecurity
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // .antMatchers(array).permitAll()
        .anyRequest()
        .authenticated();

    httpSecurity.headers().cacheControl();

    httpSecurity.addFilterBefore(
        authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
