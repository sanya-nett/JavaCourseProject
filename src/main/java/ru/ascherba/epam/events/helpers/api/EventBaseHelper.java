package ru.ascherba.epam.events.helpers.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import ru.ascherba.epam.events.helpers.config.ConfigHelper;

/**
 * Created by aleksandr.scherba on 14.02.2021
 */
public class EventBaseHelper {

    public EventBaseHelper() {
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
        RestAssured.baseURI = ConfigHelper.getHostname();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
