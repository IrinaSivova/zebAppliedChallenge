package apiTests;


import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDTO.CreateBorrowerDTO;
import api.dto.responseDTO.BorrowerDTO;
import io.restassured.http.ContentType;
import libs.TestData;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CreateBorrowerTest {
    private final String questionnaire_id = TestData.questionnaire_id;
    private final String token = TestData.token;
    ApiHelper apiHelper = new ApiHelper();


    @Test
    public void createBorrowerWithValidTelephoneNumber() {

        String telephoneNumber = apiHelper.generateValidTelephoneNumber();

        CreateBorrowerDTO createBorrowerDTO = CreateBorrowerDTO.builder()
                .first_name("Test")
                .last_name("Test")
                .salutation("ms")
                .title("Doc")
                .telephone_number(telephoneNumber)
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

        Assert.assertEquals("The telephone numbers do not match",telephoneNumber, createdBorrower.getTelephone_number());

        apiHelper.deleteBorrowById(createdBorrower.getId(), token);

    }

    @Test
    public void createBorrowWithoutTelephoneNumber() {

        CreateBorrowerDTO createBorrowerDTO = CreateBorrowerDTO.builder()
                .first_name("Test2")
                .last_name("Test2")
                .salutation("ms")
                .title("Doc")
                .is_main(true)
                .household_situation_id(0)
                .build();


        BorrowerDTO createdBorrower=
                given()
                   .contentType(ContentType.JSON)
                   .log().all()
                   .auth().oauth2(token)
                   .body(createBorrowerDTO)
                .when()
                    .post(EndPoints.POST_Borrow, questionnaire_id)
                .then()
                     .statusCode(201)
                        .extract()
                        .response().as(BorrowerDTO.class);

        apiHelper.deleteBorrowById(createdBorrower.getId(), token);

    }

}
