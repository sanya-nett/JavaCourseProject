package ru.ascherba.epam.events.helpers.config;

import org.aeonbits.owner.Config;

/**
 * Created by aleksandr.scherba on 19.02.2021
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:application.properties"
})
public interface ProjectConfig extends Config {

    @Key("app.hostname")
    String hostname();

    @Key("browser.name")
    @DefaultValue("chrome")
    String browser();

}
