package com.dstevens.users.patronages;

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
		if(id != null) {
			return dao.findOne(id);
		}
		return null;
	}
	
	public Patronage findPatronageByMembershipId(String membershipId) {
		if(membershipId != null && membershipId.length() > 4) {
			return dao.findOne(Integer.valueOf(membershipId.substring(4)));
		}
		return null;
	}
	
	public Patronage save(Patronage patronage) {
		return dao.save(patronage);
	}
	
	
	
}
