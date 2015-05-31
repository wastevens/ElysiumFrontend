package com.dstevens.troupe;

import org.springframework.stereotype.Service;

@Service
public class TroupeFactory {

    public Troupe createTroupe(String name, Venue venue) {
        return new Troupe(null, name, venue);
    }
    
    
}
