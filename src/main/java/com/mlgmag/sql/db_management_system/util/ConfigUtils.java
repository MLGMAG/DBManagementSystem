package com.mlgmag.sql.db_management_system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public final class ConfigUtils {

    private static final String FILEPATH_ENV_VAR = "configFilePath";

    private static final Logger LOG = LoggerFactory.getLogger(ConfigUtils.class);

    private ConfigUtils() {
    }

    public static String getConfigFilePath() {
        String configFilePath = System.getProperty(FILEPATH_ENV_VAR);
        if (isNull(configFilePath)) {
            String message = String.format("Environment variable '%s' is not present", FILEPATH_ENV_VAR);
            LOG.error(message);
            throw new IllegalArgumentException(message);
        }
        return configFilePath;
    }

}
