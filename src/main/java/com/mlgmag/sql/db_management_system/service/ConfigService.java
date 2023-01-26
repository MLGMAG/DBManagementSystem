package com.mlgmag.sql.db_management_system.service;

import com.mlgmag.sql.db_management_system.util.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class ConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigService.class);

    private static ConfigService instance;

    private final Properties properties = new Properties();

    private ConfigService() {
    }

    public String getConfig(String configName) {
        LOG.info("Retrieving the config '{}'", configName);
        return properties.getProperty(configName);
    }

    public void clean() {
        LOG.info("Config cleanup...");
        properties.clear();
    }

    public static void init() {
        LOG.info("Initialize configs...");
        instance = new ConfigService();

        String configFilePath = ConfigUtils.getConfigFilePath();
        LOG.info("The config path is '{}'", configFilePath);

        getInstance().loadConfigs(configFilePath);
    }

    private void loadConfigs(String configFilePath) {
        File file = new File(configFilePath);
        try (FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
        } catch (IOException ex) {
            LOG.error("Could not load configs:", ex);
            throw new RuntimeException(ex);
        }
    }

    public static ConfigService getInstance() {
        return instance;
    }
}
