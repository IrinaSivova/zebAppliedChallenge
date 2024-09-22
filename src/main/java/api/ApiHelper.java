package api;

import io.restassured.http.ContentType;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public void deleteBorrowById(String borrow_id, String token) {

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token)
                .when()
                .post(EndPoints.DELETE_Borrow, borrow_id)
                .then()
                .statusCode(204);
    }

    public String generateValidTelephoneNumber() {
        Random random = new Random();

        // Start by optionally adding parentheses around '0' and digits
        StringBuilder sb = new StringBuilder();

        boolean useParentheses = random.nextBoolean(); // Randomly decide if parentheses are used

        if (useParentheses) {
            sb.append('(');
        }

        // Always append '0' as per the pattern
        sb.append('0');

        // Append between 1 and 3 digits after '0'
        int numDigitsAfterZero = random.nextInt(3) + 1; // Generates a number between 1 and 3
        for (int i = 0; i < numDigitsAfterZero; i++) {
            sb.append(random.nextInt(10)); // Append random digits between 0-9
        }

        // Optionally close parentheses
        if (useParentheses) {
            sb.append(')');
        }

        // Append between 6 and 26 digits, optionally with spaces between them
        int totalDigits = random.nextInt(21) + 6; // Generates a number between 6 and 26
        for (int i = 0; i < totalDigits; i++) {
            if (random.nextBoolean()) {
                sb.append(' '); // Optionally add a space
            }
            sb.append(random.nextInt(10)); // Append the next digit
        }

        return sb.toString();
    }

    public String generateInvalidPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();

        // Randomly choose how to invalidate the phone number
        int choice = random.nextInt(5);

        switch (choice) {
            case 0:
                // Doesn't start with 0
                phoneNumber.append("1");
                phoneNumber.append(generateRandomDigits(random, 7 + random.nextInt(24))); // Invalid length
                break;
            case 1:
                // Invalid area code (more than 4 digits)
                phoneNumber.append("(").append(generateRandomDigits(random, 5)).append(")");
                phoneNumber.append(generateRandomDigits(random, 6));  // Adding more digits
                break;
            case 2:
                // Add non-digit characters
                phoneNumber.append("(0A12) ");
                phoneNumber.append(generateRandomDigits(random, 6));
                break;
            case 3:
                // Missing parentheses for area code, but with special characters
                phoneNumber.append("0-123 ");
                phoneNumber.append(generateRandomDigits(random, 6));
                break;
            default:
                // Completely random invalid format
                phoneNumber.append("InvalidNumber!");
        }

        return phoneNumber.toString();
    }

    // Helper method to generate a string of random digits of a given length
    private static String generateRandomDigits(Random random, int length) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            digits.append(random.nextInt(10));  // Append a random digit (0-9)
        }
        return digits.toString();
    }

}


