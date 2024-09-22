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

public class TelephoneFieldNotUniqueTest {

    private final String questionnaire_id = TestData.questionnaire_id;
    private final String token = TestData.token;
    ApiHelper apiHelper = new ApiHelper();


    @Test
    public void checkBorrowFieldNotUnique() {

        String telephoneNumber = apiHelper.generateValidTelephoneNumber();

        CreateBorrowerDTO[] createBorrowerDTO = {

                CreateBorrowerDTO.builder()
                        .first_name("User1")
                        .last_name("Test")
                        .salutation("ms")
                        .title("Doc")
                        .telephone_number(telephoneNumber)
                        .is_main(true)
                        .household_situation_id(0)
                        .build(),

                CreateBorrowerDTO.builder()
                        .first_name("User2")
                        .last_name("Test")
                        .salutation("ms")
                        .title("Doc")
                        .telephone_number(telephoneNumber)
                        .is_main(true)
                        .household_situation_id(0)
                        .build()
        };

        BorrowerDTO[] responseBorrows =
                given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token).body(createBorrowerDTO)
                .when()
                .post(EndPoints.POST_Borrow,questionnaire_id)
                .then()
                .statusCode(201).extract().as(BorrowerDTO[].class);

        Assert.assertEquals("The number of Borrows doesn't match", createBorrowerDTO.length, responseBorrows.length  );

        Assert.assertEquals("The telephone numbers do not match",responseBorrows[0].getTelephone_number(), responseBorrows[1].getTelephone_number());

        for (int i = 0; i < responseBorrows.length; i++) {
            apiHelper.deleteBorrowById(responseBorrows[i].getId(),token);

        }
    }
}
