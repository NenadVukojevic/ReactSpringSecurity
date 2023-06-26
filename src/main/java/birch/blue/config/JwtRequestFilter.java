package birch.blue.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import birch.blue.services.CustomUserService;

public class JwtRequestFilter extends OncePerRequestFilter {

	private CustomUserService customUserService;
	private JWTTokenHelper jwtTokenHelper;

	public JwtRequestFilter(CustomUserService customUserService, JWTTokenHelper jwtTokenHelper) {
		this.customUserService = customUserService;
		this.jwtTokenHelper = jwtTokenHelper;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if (null != authorizationHeader && authorizationHeader.startsWith("Bearer ")) {

			jwt = authorizationHeader.substring(7);
			username = jwtTokenHelper.getUsernameFromToken(jwt);

			if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.customUserService.loadUserByUsername(username);
				if (jwtTokenHelper.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
