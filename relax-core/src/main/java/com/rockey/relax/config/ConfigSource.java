package com.rockey.relax.config;

import com.rockey.relax.config.route.RoutesConfig;
import com.typesafe.config.Config;

public interface ConfigSource {

    public void parseLocalConfig();
    
    public Config getConfig();
    
    public RoutesConfig getRoutesConfig();
}
