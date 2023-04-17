package com.dxc.cbs.filter;

import java.io.IOException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authToken = req.getHeader("authorization");
		String jwtToken = authToken.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody();
		System.out.println("Claims: "+claims);
		chain.doFilter(request, response);
	}
	
}
