package api.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDTO {
    private String first_name;
    private String last_name;
    private String birth_name;
    private String title;
    private String birth_ate;
    private String salutation;
    private String email;
    private String street_ame;
    private String house_umber;
    private Integer apartment_umber;
    private Integer entry;
    private String zip;
    private String city;
    private String citizenship;
    private String profession;
    private String sector_employer;
    private Boolean employed_gte_a_year;
    private Integer monthly_net_salary;
    private Boolean lives_together;
    private Boolean privacy_accepted;
    private Boolean tnc_accepted;
    private String telephone_number;
    private String id;
    private String loanQuestionnaire_id;
    private
    List<String> salary_statement_files;
    private List<String> id_document_files;
    private Boolean is_main;
    private String household_situation_id;
}
