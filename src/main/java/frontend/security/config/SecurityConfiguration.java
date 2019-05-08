package frontend.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		publicAccessForNonRegisteredUsers(http);	
		restrictedAccesForRegUsers(http);
		restrictedAccesForAdminOnly(http);
	}

	private void restrictedAccesForAdminOnly(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/adminarea/**").hasRole("ADMIN")
		   .and()
		    .httpBasic();
	}

	private void restrictedAccesForRegUsers(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/memberarea/**").hasRole("USER")
		   .and()
		    .httpBasic();
	}

	private void publicAccessForNonRegisteredUsers(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().
		antMatchers("/css/**", "/img/**", "/js/**", "/","/regnewuser",
				"/newUserAddConfirmation","/loginAsUserToJobManger", "/recoverUserPass")
		.permitAll()
		.and()
		.formLogin()
	    .loginPage("/")
	    .and()
	    .httpBasic();
	}
}