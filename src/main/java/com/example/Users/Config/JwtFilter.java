package com.example.Users.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.Service.CustomerUserdetailsService;
import com.example.Users.entity.Users;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsersRepository repository;

	@Autowired
	private CustomerUserdetailsService customerUserdetailsService;

	public static int id = 0;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestHeader = request.getHeader("Authorization");

		String jwttoken = null;

		String username = null;
		String type = null;

		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			jwttoken = requestHeader.substring(7);

			try {
				type = this.jwtTokenUtil.getTypeFromToken(jwttoken);

				System.out.println(type);

				if (type.equals("Access")) {

					username = this.jwtTokenUtil.getUsernameFromToken(jwttoken);

					System.err.println(username);

					Users employee = this.repository.findByEmailIgnoreCase(username);

					id = employee.getId();

					System.err.println(id);
				} else {
					System.out.println("Error");
				}

			} catch (Exception e) {
				new ResponseEntity<>(
						new com.example.Users.Responce.ErrorMessage("Error in Token or Your Token s invelid", "Error"),
						HttpStatus.UNAUTHORIZED);

			}

		} else {

			System.err.println("123- first in filter");
			System.out.println("Your token is not valid ");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null
				&& type.equals("Access")) {

			// GET USER DETAILS OF USER
			UserDetails details = this.customerUserdetailsService.loadUserByUsername(username);
			System.err.println("This is authorities" + details.getAuthorities());
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(details, null,
					details.getAuthorities());

			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			// After setting the Authentication in the context, we specify
			// that the current user is authenticated. So it passes the
			// Spring Security Configurations successfully.
			SecurityContextHolder.getContext().setAuthentication(upat);

		} else {
			System.out.println("Username is not valid 12312 ");

//			response.sendError(401, "Your Token Is Not Valid");
		}

		filterChain.doFilter(request, response);

	}

}
