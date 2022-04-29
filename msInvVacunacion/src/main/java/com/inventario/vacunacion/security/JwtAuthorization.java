package com.inventario.vacunacion.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inventario.vacunacion.entity.TokenEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtAuthorization extends OncePerRequestFilter{
		
	private String header = "";
	private String prefix = "";
	private String clientId = "";
	private String secretId = "";
	int expiryTime = 0;
	
	public JwtAuthorization() {
		
		this.header   = "Authorization";
		this.prefix   = "Bearer ";
		this.clientId = "msInvVacunacion";
		this.secretId = "xYhOUyhjjs12";
		this.expiryTime = 600000;//10 minutos
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
					SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(header).replace(prefix, "");
		return Jwts.parser().setSigningKey(secretId.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(header);
		if (authenticationHeader == null || !authenticationHeader.startsWith(prefix))
			return false;
		return true;
	}
	
	public TokenEntity getJwtToken() {
		
		String token = "";
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(System.currentTimeMillis() + expiryTime);
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		token = Jwts
				.builder()
				.setId(clientId)//softtekJWT
				.setSubject(clientId)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(startDate)
				.setExpiration(endDate)
				.signWith(SignatureAlgorithm.HS512,
						secretId.getBytes()).compact();
		
		TokenEntity tokenE = new TokenEntity();
		tokenE.setAccessToken(prefix + token);
		tokenE.setExpiryTime(expiryTime);
		tokenE.setStartDate(startDate);
		tokenE.setEndDate(endDate);
		
		return tokenE;
	}
	
}
