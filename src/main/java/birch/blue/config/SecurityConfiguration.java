package birch.blue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import birch.blue.services.CustomUserService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserService customUserService;
	
	@Autowired
	JWTTokenHelper jwtTokenHelper;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	/*	auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin"))
		.authorities("USER", "ADMIN");
		*/
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

        http = http.cors().and().csrf().disable();
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().exceptionHandling();
       
        http.addFilterBefore(new JwtRequestFilter(customUserService, jwtTokenHelper ), UsernamePasswordAuthenticationFilter.class);
        
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .antMatchers("/user/**").hasAuthority("USER")
            .anyRequest()
            .authenticated()
            ;			

       //         http.formLogin();
        


       // http.httpBasic();

	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
