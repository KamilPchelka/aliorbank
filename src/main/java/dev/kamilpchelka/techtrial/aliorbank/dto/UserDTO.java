package dev.kamilpchelka.techtrial.aliorbank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    String firstName;
    String lastName;
    Date dateOfBirth;
    BigDecimal balance;

    public UserDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public UserDTO(String firstName, String lastName, Date dateOfBirth, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
    }
}
