package api.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBorrowerDTO {
    private String first_name;
    private String last_name;
    private String salutation;
    private String title;
    private String telephone_number;
    private String loan_questionnaire_id;
    private Boolean is_main;
    private Integer household_situation_id;
}
