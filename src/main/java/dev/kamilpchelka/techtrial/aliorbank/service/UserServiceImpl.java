package dev.kamilpchelka.techtrial.aliorbank.service;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;
import dev.kamilpchelka.techtrial.aliorbank.model.User;
import dev.kamilpchelka.techtrial.aliorbank.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static dev.kamilpchelka.techtrial.aliorbank.controller.UserController.BALANCE_ALLOWED_OPERATIONS;

/**
 * UserServiceImpl for User related handling.
 *
 * @author Kamil Pchelka
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO retrieveUser(Long id) {
        return userRepository.retrieveUser(id);
    }

    @Override
    public List<UserDTO> retrieveUser() {
        return userRepository.retrieveUser();
    }

    @Override
    public boolean updateBalance(Long userId, @NotNull BigDecimal balance, String operation) {
        if (!BALANCE_ALLOWED_OPERATIONS.contains(operation)) {
            return false;
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        BigDecimal userBalance = user.getBalance();
        if (operation.equals("increase")) {
            user.setBalance(userBalance.add(balance));
        } else if (operation.equals("decrease")) {
            user.setBalance(userBalance.subtract(balance));
        }
        userRepository.save(user);
        return true;
    }
}
