/**
 * SecurityConfiguration.java
 */
package com.mycompany.ratesapiservice.security;

public class SecurityConfiguration //extends WebSecurityConfigurerAdapter
{/*
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin").password("admin").roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/").access("hasRole('USER')").and().formLogin();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
*/}
