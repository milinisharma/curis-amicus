package com.curisamicus.repository.auth.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.curisamicus.domain.Address;
import com.curisamicus.domain.ERole;
import com.curisamicus.domain.GenericSequence;
import com.curisamicus.domain.Role;
import com.curisamicus.domain.User;
import com.curisamicus.repository.auth.IUserRepository;
import com.curisamicus.service.utils.SequenceGeneratorService;

@Repository
public class UserRepositoryImpl implements IUserRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SequenceGeneratorService seqGenService;
	
	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
	
	@Override
	public User findByUsername(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return mongoTemplate.findOne(query, User.class);
	}
	
	@Override
	public User findByEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public User registerUser(User user)  {
		Address address = user.getAddress();
		address.setId(String.valueOf(seqGenService.generateSequence(GenericSequence.SEQUENCE_NAME)));
		user.setAddress(address);
		
		return mongoTemplate.save(user);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return (findByUsername(username) != null) ? true : false;
	}

	@Override
	public Boolean existsByEmail(String email) {
		return(findByEmail(email) != null) ? true : false;
	}

	@Override
	public Role findByName(ERole name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("role").is(name));
		return mongoTemplate.findOne(query, Role.class);
	}

	

	
	
}
