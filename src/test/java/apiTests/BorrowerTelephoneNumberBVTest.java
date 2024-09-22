package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDTO.CreateBorrowerDTO;
import api.dto.responseDTO.BorrowerDTO;
import api.dto.responseDTO.ValidationErrorDTO;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static libs.TestData.questionnaire_id;
import static libs.TestData.token;

public class BorrowerTelephoneNumberBVTest {

    String telephoneNumberBVLowLimit = "01 234 5678"; // 8 digits
    String telephoneNumberBVHighLimit= "(0123) 45 6789 0123 4567 8901 2345 6789"; // 30 digits
    String telephoneNumberBVLeftLowLimit = "01 234 567"; // 7 digits
    String telephoneNumberBVRightHighLimit= "(0123) 45 6789 0123 4567 8901 2345 67890"; //31 digits
    ApiHelper apiHelper = new ApiHelper();

    public void checkBoundaryValuesTelNumberLow () {

        CreateBorrowerDTO createBorrowerDTO = CreateBorrowerDTO.builder()
                .first_name("Test")
                .last_name("Test")
                .salutation("ms")
                .title("Doc")
                .telephone_number(telephoneNumberBVLowLimit)
                .is_main(true)
                .household_situation_id(0)
                .build();


        BorrowerDTO createdBorrower =
                given()
                  .contentType(ContentType.JSON)
                  .log().all()
                  .auth().oauth2(token).body(createBorrowerDTO)
                .when()
                  .post(EndPoints.POST_Borrow,questionnaire_id)
                .then()
                  .statusCode(201)
                  .log().all()
                  .extract()
                  .response().as(BorrowerDTO.class);

        Assert.assertEquals("The telephone numbers do not match",telephoneNumberBVLowLimit, createdBorrower.getTelephone_number());

        apiHelper.deleteBorrowById(createdBorrower.getId(), token);

    };

    @Test
    public void checkBoundaryValuesTelNumberLeftLow () {

        CreateBorrowerDTO createBorrowerDTO = CreateBorrowerDTO.builder()
                .first_name("Test")
                .last_name("Test")
                .salutation("ms")
                .title("Doc")
                .telephone_number(telephoneNumberBVLeftLowLimit)
                .is_main(true)
                .household_situation_id(0)
                .build();


        ValidationErrorDTO recievedValidationError =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth().oauth2(token)
                        .body(createBorrowerDTO)
                        .when()
                        .put(EndPoints.UPDATE_Borrow, questionnaire_id)
                        .then()
                        .statusCode(422)
                        .log().all()
                        .extract()
                        .response()
                        .as(ValidationErrorDTO.class);

        Assert.assertEquals("The message is incorrect",
                "\"VALIDATION WARNING MESSAGE\"",
                recievedValidationError.getMsg());

    };

    // To DO
    // Verify BV = 30
    // Verify BV + 1

}
