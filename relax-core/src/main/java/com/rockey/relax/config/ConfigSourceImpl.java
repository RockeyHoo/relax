package com.rockey.relax.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rockey.relax.config.route.RoutesConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Component
public class ConfigSourceImpl implements ConfigSource {

    protected Config config;
    
    @Autowired protected RoutesConfig routesConfig;

    public ConfigSourceImpl() {
        log.info("[fuse] Loading config");
        Config base = ConfigFactory.load("relax-base.conf");
        
        this.config = ConfigFactory.load("relax.conf")
                                   .withFallback(base);
    }
    
    @Override
    public Config getConfig() {
        return this.config;
    }
    
    @Override
    public RoutesConfig getRoutesConfig() {
        return this.routesConfig;
    }
    
    @Override
    public void parseLocalConfig() {
        routesConfig.parse();
    }

    protected static final Logger log = LoggerFactory.getLogger(ConfigSourceImpl.class);
    
}
