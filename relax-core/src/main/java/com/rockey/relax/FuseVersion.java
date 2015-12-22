package com.rockey.relax;

import org.springframework.stereotype.Component;

@Component
public class FuseVersion {

    String version = "Fuse v0.0.3-SNAPSHOT";
    
    public FuseVersion() {
        super();
    }
    
    public String toString() {
        return version;
    }
}
