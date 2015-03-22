package com.dstevens.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatronageRepository {

	private PatronageDao dao;

	@Autowired
	public PatronageRepository(PatronageDao dao) {
		this.dao = dao;
	}

	public Iterable<Patronage> findAllUndeleted() {
		return dao.findAll();
	}

	public Patronage findPatronage(Integer id) {
		return dao.findOne(id);
	}
	
	public Patronage findPatronageByMembershipId(String membershipId) {
		return dao.findOne(Integer.valueOf(membershipId.substring(4)));
	}
	
	public void save(Patronage patronage) {
		dao.save(patronage);
	}
	
	
	
}
