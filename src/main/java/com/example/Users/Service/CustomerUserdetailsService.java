package com.example.Users.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.Cache;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Users.Redis.RedisService;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private UsersService service;

	@Autowired
	private RedisService redisService;

	@Override
	public UserDetails loadUserByUsername(String username) {

		
		ArrayList<SimpleGrantedAuthority> permissions = null;
		
		Logger LOG=LoggerFactory.getLogger(CustomerUserDetailsService.class);
		
//		if (!redisService.isKeyExist(username, username)) {
//			
//			System.err.println("user get from database");
//			redisService.addInCache(username, username, users.toString());
//			
//		} else {
//
//			String jsonString = (String) redisService.getFromCache(username, username);
//
//			System.err.println("get from cache");
//			try {
//
//				ObjectMapper mapper = new ObjectMapper();
//				
//				Map<String, Object> map = mapper.readValue(jsonString, Map.class);
//				System.out.println(map.toString());
//				users.setEmail((String) (map.get("email")));
//				users.setPassword((String) map.get("password"));
//				users.setId((Integer) map.get("id"));
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				
//			}
//
//			
//
//		}
		
		
		Users userEntity = new Users();
        if (!redisService.isKeyExist(username, username)) {
        	
        
        	
            userEntity = this.repository.findByEmailIgnoreCase(username);
            LOG.info("get from database");
            redisService.addInCache(username, username, userEntity.toString());
        } 
        else {

            String jsonString = (String) redisService.getFromCache(username, username);
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(jsonString, Map.class);
                System.out.println(map.toString());
                LOG.info("get from cache");
                userEntity.setPassword((String) map.get("password"));
                userEntity.setEmail((String) map.get("email"));
                userEntity.setId(((Integer) map.get("id")));
            } catch (Exception e) {
                System.out.println("ERROR " + e);
            }
            System.out.println("JSON STRING 22 " + userEntity.toString());
        }
        
       

        if (userEntity != null) {

        	permissions = this.service.getAuthorities(userEntity.getId());

        	System.out.println(permissions);
		}

		else {
			throw new ResourceNotFoundException();
		}
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
        		permissions);
    }
		
		
		
		
			}
	


