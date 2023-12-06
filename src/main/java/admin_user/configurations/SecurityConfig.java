package admin_user.configurations;

import admin_user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final SecurityContextRepository securityContextRepository;
	private final String[] publicRoutes = {
			"/jeduler/auth",
	};
	private final String[] adminRoutes = {
			"/jeduler/admin/**"
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
								.requestMatchers(adminRoutes).hasAuthority(
										Role.ADMIN.name()
								)
								.requestMatchers("/jeduler/user-page").hasAnyAuthority(
										Role.USER.name(),
										Role.ADMIN.name()
								)
								.anyRequest().authenticated()
				)
				.sessionManagement(
						session -> session
							.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
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
