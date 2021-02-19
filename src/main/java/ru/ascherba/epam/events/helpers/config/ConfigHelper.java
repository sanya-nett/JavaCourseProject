package ru.ascherba.epam.events.helpers.config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Created by aleksandr.scherba on 19.02.2021
 */
public class ConfigHelper {

    private static ProjectConfig getConfig() {
        return ConfigFactory.newInstance().create(ProjectConfig.class, System.getProperties());
    }

    public static String getHostname() {
        return getConfig().hostname();
    }

    public static String getBrowser() {
        return getConfig().browser();
    }

}
