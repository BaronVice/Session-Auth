package admin_user.configurations;

import admin_user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import admin_user.service.CustomSuccessHandler;
import admin_user.service.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
//	private final CustomSuccessHandler customSuccessHandler;
	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository;
	private final String[] publicRoutes = {
			"/jeduler/auth",
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
				.csrf(AbstractHttpConfigurer::disable)
//				.cors(AbstractHttpConfigurer::disable)
//				.httpBasic(AbstractHttpConfigurer::disable)
//				.authenticationManager(authenticationManager)
//				.formLogin(AbstractHttpConfigurer::disable)
				.securityContext(
						context -> context
								.securityContextRepository(securityContextRepository)
				)
				.authorizeHttpRequests(
						request -> request
								.requestMatchers(publicRoutes).permitAll()
								.requestMatchers("/jeduler/pp/admin-page").hasAuthority(
										Role.ADMIN.name()
								)
								.requestMatchers("/jeduler/pp/user-page").hasAnyAuthority(
										Role.USER.name(),
										Role.ADMIN.name()
								)
								.anyRequest().authenticated()
				)
				.sessionManagement(
						session -> session
							.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
							.maximumSessions(2)
							.maxSessionsPreventsLogin(false)
//							.expiredUrl("/login")
				)
//				.formLogin(
//						form -> form
//								.loginPage("/login")
//								.loginProcessingUrl("/jeduler/auth")
//								.successHandler(customSuccessHandler).permitAll()
//				)
				.logout(
						form -> form
								.invalidateHttpSession(true)
								.clearAuthentication(true)
								.deleteCookies("JSESSIONID")
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//								.logoutSuccessUrl("/login?logout")
								.permitAll()
				);
		
		return http.build();
		
	}

}
