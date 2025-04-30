package com.practice.repo.components.api.fakeRestApi;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

import static com.practice.repo.enums.ResourceType.API;

@Component
@Resource(type = API)
public class FakeRESTApi extends BaseComponent {

    @Step
    public FakeRESTApi getBook(String id) {
        Response response = restAssured()
                .pathParam("id", id)
                .get("/api/v1/Books/{id}");
        response.then().assertThat().statusCode(200);
        return this;
    }

}
