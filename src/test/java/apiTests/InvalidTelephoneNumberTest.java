package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDTO.CreateBorrowerDTO;
import api.dto.responseDTO.ValidationErrorDTO;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static libs.TestData.questionnaire_id;
import static libs.TestData.token;
import static org.hamcrest.CoreMatchers.equalTo;

public class InvalidTelephoneNumberTest {
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void checkValidationTelephoneNumber () {

        String telephoneNumber = apiHelper.generateInvalidPhoneNumber();

        CreateBorrowerDTO createBorrowerDTO = CreateBorrowerDTO.builder()
                .first_name("Test")
                .last_name("Test")
                .salutation("ms")
                .title("Doc")
                .telephone_number(telephoneNumber)
                .is_main(true)
                .household_situation_id(0)
                .build();


        ValidationErrorDTO receivedValidationError =
                given()
                  .contentType(ContentType.JSON)
                  .log().all()
                  .auth().oauth2(token).body(createBorrowerDTO)
                .when()
                  .post(EndPoints.POST_Borrow,questionnaire_id)
                .then()
                  .statusCode(422)
                  .log().all()
                        .extract()
                        .as(ValidationErrorDTO.class)
                ;
        Assert.assertEquals("WARNING MESSAGE",receivedValidationError.getMsg());

    }
}
