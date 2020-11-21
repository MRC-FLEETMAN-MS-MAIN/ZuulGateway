package com.learnprogramming.photoapp.api.gateway.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment environment;
	
	public WebSecurity(Environment environment) {
		this.environment = environment;
	}
	
	protected void configure (HttpSecurity http) throws Exception {

		http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
               .antMatchers(HttpMethod.POST,environment.getProperty("api.registration.url.path")).permitAll()
               .antMatchers(HttpMethod.POST,environment.getProperty("api.login.url.path")).permitAll()
				/*
                .antMatchers(HttpMethod.GET,"/vehicle/fetch").permitAll()
                .antMatchers(HttpMethod.GET,"/position/fetch/village_truck").permitAll()
                .antMatchers(HttpMethod.GET,"/test-microservice/rest/publish/fetchid").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()  */
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(),environment));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //make sure client doesnt cache data ( so that auth header is to be provided mandatorily)

	
		
	}
}
