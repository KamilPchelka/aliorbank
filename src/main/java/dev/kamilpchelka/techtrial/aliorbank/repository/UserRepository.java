package dev.kamilpchelka.techtrial.aliorbank.repository;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;
import dev.kamilpchelka.techtrial.aliorbank.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("Select new dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO(u.firstName, u.lastName, u.dateOfBirth, u.balance) " +
            "FROM User u WHERE u.id = :id")
    UserDTO retrieveUser(@Param("id") Long id);

    @Query("Select new dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO(u.firstName, u.lastName) FROM User u")
    List<UserDTO> retrieveUser();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.balance = CASE \n" +
            "WHEN :operation = 'decrease' THEN (u.balance - :balance)\n" +
            "WHEN :operation = 'increase' THEN (u.balance + :balance)\n" +
            "ELSE u.balance\n" +
            "END\n" +
            "WHERE u.id = :userId")
    int updateBalance(@Param("userId") Long userId, @Param("balance") BigDecimal balance, String operation);
}
