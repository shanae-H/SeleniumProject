package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiRequest extends SpecBuilder{


    public static Response post( Headers headers, HashMap<String,Object> formParams, Cookies cookies,String endpoint){
        return given(getRequestSpec())
                    .headers(headers)
                    .formParams(formParams)
                    .cookies(cookies)
                    .log().all()
                .when()
                    .post(endpoint)
                .then()
                    .log()
                    .all()
                    .extract()
                    .response();
    }

    public static Response get (Cookies cookies, String endpoint){
        return given(getRequestSpec())
                    .cookies(cookies)
                .when()
                    .get(endpoint)
                .then()
                    .log()
                    .all()
                    .extract()
                    .response();
    }
}
