package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Config.JwtFilter;
import com.example.Users.Repository.QueryPortalRepository;
import com.example.Users.entity.QueryPortal;
import com.example.Users.entity.QueryPortalDTO;

@Service
public class QueryService {

	@Autowired
	private QueryPortalRepository portalRepository;

	@Autowired
	private JwtFilter filter;

	public QueryPortal setQuery(QueryPortalDTO dto) {
		QueryPortal portal = new QueryPortal();

		portal.setId(dto.getId());

		portal.setQuery(dto.getQuery());
		portal.setUsers1();

	}
}
