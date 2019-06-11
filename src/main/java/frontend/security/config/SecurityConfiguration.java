package frontend.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		publicAccessForNonRegistereAndRegisteredUsers(http);	
		restrictedAccesForAdminOnly(http);
	}

	private void restrictedAccesForAdminOnly(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/adminarea/**").hasRole("ADMIN")
		   .and()
		    .httpBasic();
	}

	private void publicAccessForNonRegistereAndRegisteredUsers(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().
		antMatchers("/css/**", "/img/**", "/js/**", "/","/regnewuser",
				"/newUserAddConfirmation","/loginAsUserToJobManger", "/recoverUserPass")
		.permitAll()
		.antMatchers("/memberarea/**").hasRole("USER")
		.and()
		.formLogin().loginPage("/")
	    .and()
	    .httpBasic();
	}
}