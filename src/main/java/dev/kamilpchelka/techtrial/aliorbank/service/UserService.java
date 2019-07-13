package dev.kamilpchelka.techtrial.aliorbank.service;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserService interface for Users.
 *
 * @author Kamil Pchelka
 */
public interface UserService {
    UserDTO retrieveUser(Long id);

    List<UserDTO> retrieveUser();

    boolean updateBalance(Long userId, BigDecimal balance, String operation);
}
