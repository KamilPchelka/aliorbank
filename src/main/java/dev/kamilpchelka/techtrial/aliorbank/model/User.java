package dev.kamilpchelka.techtrial.aliorbank.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 5907613310786425798L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "balance")
    BigDecimal balance;
}
