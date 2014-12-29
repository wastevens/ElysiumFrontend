package com.dstevens.troupes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.players.Setting;

@Service
public class TroupeRepository {

    private final TroupeFactory factory;
    private TroupeDao dao;

    @Autowired
    public TroupeRepository(TroupeDao dao, TroupeFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    public Troupe ensureExists(String troupeName, Setting setting) {
        Troupe troupe = dao.findUndeletedNamed(troupeName);
        if (troupe != null) {
            return troupe;
        }
        return dao.save(factory.createTroupe(troupeName, setting));
    }

    public Troupe findWithId(String id) {
    	return dao.findOne(id);
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
