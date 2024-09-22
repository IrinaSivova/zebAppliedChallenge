package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import libs.TestData;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertTrue;

public class GetBorrowerTest {
    String questionnaire_id = TestData.questionnaire_id;
    String borrower_id = TestData.borrower_id;
    String token = TestData.token;

    @Test
    public void getBorrowerById(){

        Response response=
                given()
                  .contentType(ContentType.JSON)
                .pathParams("questionnaire_id", questionnaire_id,"borrower_id", borrower_id)
                  .log().all()
                  .auth().oauth2(token)
                .when()
                  .get(EndPoints.base_url + "/questionnaire/{questionnaire_id}/borrower/{borrower_id}")
                .then()
                  .statusCode(200)
                  .log().all()
                  .extract().response();

        assertTrue("The response does not contain the 'telephone_number' field", response.jsonPath().getMap("$").containsKey("telephone_number"));

    }

    @Test
    public void checkJsonSchema(){

        given()
                  .contentType(ContentType.JSON)
                  .pathParams("questionnaire_id", questionnaire_id,"borrower_id", borrower_id)
                  .log().all()
                  .auth().oauth2(token)
                .when()
                  .get(EndPoints.base_url + "/questionnaire/{questionnaire_id}/borrower/{borrower_id}")
                .then()
                  .statusCode(200)
                  .log().all()
                  .assertThat().body(matchesJsonSchemaInClasspath("responseschema.json"));

    }
}
