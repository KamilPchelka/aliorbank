package dev.kamilpchelka.techtrial.aliorbank.repository;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;
import dev.kamilpchelka.techtrial.aliorbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("Select new dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO(u.firstName, u.lastName, u.dateOfBirth, u.balance) " +
            "FROM User u WHERE u.id = :id")
    UserDTO retrieveUser(@Param("id") Long id);

    @Query("Select new dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO(u.firstName, u.lastName) FROM User u")
    List<UserDTO> retrieveUser();
}
