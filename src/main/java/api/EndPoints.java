package api;

import libs.TestData;

public interface EndPoints {
    String base_url = "https://api.dev.baufi.zeb-applied.com/api/v1";

    String GET_Borrow = base_url + "/questionnaire/{0}/borrower/{0}";

    String POST_Borrow = base_url + "/questionnaire/{0}/borrower";

    String UPDATE_Borrow = base_url + "/questionnaire/{0}/borrower/{0}";

    String DELETE_Borrow =  base_url + "/questionnaire/" + TestData.questionnaire_id + "/borrower/{0}";
}
