package com.ssafy.sonmogaji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	// 인증 또는 인가에 대한 설정
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 로그인
		http.csrf().disable();
//				.exceptionHandling();

		http.authorizeRequests()
				.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
						"/**/*.css", "/**/*.js")
				.permitAll() // 특정 URL을 설정하며, permitAll은 해당 URL의 접근을 인증없이 허용한다는 의미
				.antMatchers("/**").permitAll()
				.antMatchers("/v3/api-docs", "/swagger-ui", "/swagger-resources/**").permitAll().anyRequest()
				.authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

}
