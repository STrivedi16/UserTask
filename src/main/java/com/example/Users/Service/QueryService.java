package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Config.JwtFilter;
import com.example.Users.Repository.QueryPortalRepository;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.QueryPortal;
import com.example.Users.entity.QueryPortalDTO;
import com.example.Users.entity.Users;

@Service
public class QueryService {

	@Autowired
	private QueryPortalRepository portalRepository;

	@Autowired
	private JwtFilter filter;

	@Autowired
	private UsersRepository repository;

	public QueryPortal setQuery(QueryPortalDTO dto) throws Exception {
		QueryPortal portal = new QueryPortal();

		portal.setId(dto.getId());

		portal.setQuery(dto.getQuery());

		Users users = this.repository.findById(filter.id).orElseThrow(() -> new Exception("User not found"));

		portal.setUsers1(users);

		return this.portalRepository.save(portal);

	}
}
