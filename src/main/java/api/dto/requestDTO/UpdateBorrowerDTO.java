package api.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBorrowerDTO {

    private String firstName;
    private String lastName;
    private String salutation;
    private String title;
    private String telephoneNumber;
    private String birthName;
    private String birthDate;
    private String email;
    private String streetName;
    private String houseNumber;
    private Integer apartmentNumber;
    private Integer entry;
    private String zip;
    private String city;
    private String citizenship;
    private String profession;
    private String sectorEmployer;
    private Boolean employedGteAYear;
    private Integer monthlyNetSalary;
    private Boolean livesTogether;
    private Boolean privacyAccepted;
    private Boolean tncAccepted;
}
