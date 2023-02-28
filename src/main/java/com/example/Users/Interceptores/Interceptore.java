package com.example.Users.Interceptores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.Users.Config.JwtTokenUtil;
import com.example.Users.Redis.RedisService;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Service.UsersService;
import com.example.Users.entity.UserRoleEntity;
import com.example.Users.entity.Users;

@Component
public class Interceptore implements HandlerInterceptor {

	private org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Interceptore.class);

	@Autowired
	private UsersRepository repository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsersService service;
	
	@Autowired
	private RedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		LOG.info("preHandle interceptor invoked....{}:{}" + request.getRequestURL(), request.getMethod());

		System.err.println("This is preHandler");

		String authHerader = request.getHeader("Authorization");
		// String token = (null != authHerader) ? authHerader.split(" ")[1] : null;
//
//		System.out.println(token);

		String token;
		if (authHerader != null && authHerader.startsWith("Bearer ")) {

			token = authHerader.substring(7);

			//System.out.println(token);
			String username = this.jwtTokenUtil.getUsernameFromToken(token);
			System.out.println(username);

			Users users = this.repository.findByEmailIgnoreCase(username);

//			ArrayList<SimpleGrantedAuthority> permission = this.service.getAuthorities(users.getId());
//
//			List<UserRoleEntity> list = users.getRole();
//
//			System.err.println("Permission that get in interceptor" + permission);
//
//			request.setAttribute(username, list);
//			request.setAttribute("permission ", permission);
			
			ArrayList<SimpleGrantedAuthority> permission = null;
			
			
			if(!redisService.isKeyExist(users.getId()+"permission", users.getId()+"permission"))
			{
				permission=this.service.getAuthorities(users.getId());
				
				System.err.println("cache in interceptor...");
				redisService.addInCache(users.getId()+"permission",users.getId()+"permission", permission);
			}
			else {
				
				System.err.println("cache in interceptor...");
				
				permission=(ArrayList<SimpleGrantedAuthority>) redisService.getFromCache(users.getId()+"permission",users.getId()+"permission");
			}
			
			request.setAttribute("permission ", permission);
		}

//		String username = request.getParameter("username");
//
//		System.out.println(username);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		LOG.info("postHandler interceptor invked.......{}:{}"+request.getRequestURL(),request.getMethod());
			
		
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
