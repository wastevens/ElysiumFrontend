package com.dstevens.troupe;

import org.springframework.stereotype.Service;

import com.dstevens.player.Setting;

@Service
public class TroupeFactory {

    public Troupe createTroupe(String name, Setting setting) {
        return new Troupe(null, name, setting);
    }
    
    
}
