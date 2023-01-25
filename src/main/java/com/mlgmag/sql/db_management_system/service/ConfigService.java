package com.mlgmag.sql.db_management_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.util.Objects.isNull;

public final class ConfigService {

    public static final String FILEPATH_ENV_VAR = "configFilePath";

    private static final Logger LOG = LoggerFactory.getLogger(ConfigService.class);

    private static final Properties PROPERTIES = new Properties();

    private ConfigService() {
    }

    public static void init() {
        LOG.info("Initialize configs...");

        String configFilePath = System.getProperty(FILEPATH_ENV_VAR);
        if (isNull(configFilePath)) {
            String message = String.format("Environment variable '%s' is not present", FILEPATH_ENV_VAR);
            LOG.error(message);
            throw new IllegalArgumentException(message);
        }

        LOG.info("The config path is '{}'", configFilePath);

        File file = new File(configFilePath);
        try (FileReader fileReader = new FileReader(file)) {
            PROPERTIES.load(fileReader);
        } catch (IOException ex) {
            LOG.error("Could not load configs:", ex);
            throw new RuntimeException(ex);
        }
    }

    public static void clean() {
        LOG.info("Config cleanup...");
        PROPERTIES.clear();
    }

    public static String getConfig(String configName) {
        LOG.info("Retrieving the config '{}'", configName);
        return PROPERTIES.getProperty(configName);
    }
}
