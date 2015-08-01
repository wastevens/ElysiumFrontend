package com.dstevens.troupe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroupeRepository {

    private final TroupeFactory factory;
    private TroupeDao dao;

    @Autowired
    public TroupeRepository(TroupeDao dao, TroupeFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    public Troupe ensureExists(String troupeName, Venue venue) {
        Troupe troupe = dao.findUndeletedNamed(troupeName);
        if (troupe != null) {
            return troupe;
        }
        return dao.save(factory.createTroupe(troupeName, venue));
    }

    public Troupe findWithId(Integer id) {
    	Troupe troupe = dao.findOne(id);
    	if(troupe == null) {
    		throw new UnknownTroupeException("Could not find troupe with id " + id);
    	}
		return troupe;
    }
    
    public Optional<Troupe> findOptionalWithId(Integer id) {
    	return Optional.ofNullable(dao.findOne(id));
    }
    
    public Troupe findNamed(String troupeName) {
        return dao.findUndeletedNamed(troupeName);
    }
    
    public Iterable<Troupe> findAllUndeleted() {
    	return dao.findAllUndeleted();
    }

	public void delete(Troupe troupe) {
		dao.delete(troupe);
	}
	
	public Troupe save(Troupe troupe) {
		return dao.save(troupe);
	}
}
