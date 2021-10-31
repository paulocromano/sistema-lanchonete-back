package br.com.sistemalanchonete.seguranca.jwt;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String headerAuthorization = request.getHeader("Authorization");
		
		if (Objects.nonNull(headerAuthorization) && headerAuthorization.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authorization = getAuthentication(headerAuthorization.substring(7));
			
			if (Objects.nonNull(authorization)) {
				SecurityContextHolder.getContext().setAuthentication(authorization);
			}
		}
		
		chain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}
		
		return null;
	}
}
