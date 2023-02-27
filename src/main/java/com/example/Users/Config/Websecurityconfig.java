package com.example.Users.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.Users.Service.CustomerUserDetailsService;

@Configuration // TO GENERATE BEAN AND WHILE EXCUTION TO ADD BEAN
@EnableWebSecurity // TO PROVIDE A SECURUTY
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String[] PULIC_URL= {
			"/register", "/token","/v3/api-docs"
			,"/v2/api-docs"
			,"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",
			"/forgot",
			"/loginwithOTP"
			,"/mobile",
"/registerOtp"
	};
	
	
	
	@Autowired
	private CustomerUserDetailsService customerUserdetailsService;

	@Autowired
	private JwtAuthEntryPoint authEntryPoint;

	@Autowired
	private JwtFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable().authorizeRequests() // we dont need cros so we disale it
				.antMatchers(PULIC_URL).permitAll()// we allowed only this api to public
				
				.anyRequest().authenticated() // this is not allowed other value

				.and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().exceptionHandling().authenticationEntryPoint(authEntryPoint);

		System.err.println("3 in web config");

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserdetailsService);
	}

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
