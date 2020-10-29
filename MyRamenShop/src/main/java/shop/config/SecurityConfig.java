package shop.config;

import java.util.List;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import shop.repository.UserRepository;

@Configuration
public class SecurityConfig {

	@Autowired
	UserDetailsService demoUserDetailsService;

	@Configuration
	@Order(2)
	static class adminConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("admin").and().formLogin()
					.loginPage("/admin").successForwardUrl("/admin/orderList").permitAll().and().logout()
					.logoutUrl("/admin/logout").permitAll().logoutSuccessUrl("/admin").and().csrf().disable();

		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.inMemoryAuthentication()

					.passwordEncoder(new BCryptPasswordEncoder()).withUser("123")
					.password(new BCryptPasswordEncoder().encode("123")).roles("admin");
		}
	}

	@Configuration
	@Order(1)
		class memberConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.antMatcher("/user/**").authorizeRequests().antMatchers("/user/signup").permitAll().anyRequest().hasRole("User").and().formLogin()
					.loginPage("/user").successForwardUrl("/user/memberCenter").permitAll().and().logout()
					.logoutUrl("/user/logout").permitAll().logoutSuccessUrl("/user").and().csrf().disable();

		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(demoUserDetailsService);
		}

	}

}
