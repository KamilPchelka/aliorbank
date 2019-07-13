package dev.kamilpchelka.techtrial.aliorbank.controller;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;
import dev.kamilpchelka.techtrial.aliorbank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    public static final List<String> BALANCE_ALLOWED_OPERATIONS = Arrays.asList("decrease", "increase");
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.retrieveUser();
        if (userDTOS == null || userDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping(path = "/api/users/{id}")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable("id") long userId) {
        UserDTO userDTO = userService.retrieveUser(userId);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping(path = "/api/users/{id}/balance")
    public ResponseEntity updateBalance(@PathVariable("id") long id, @RequestBody UserDTO userDTO,
                                        @RequestParam String operation) {
        operation = operation.toLowerCase();
        if (!BALANCE_ALLOWED_OPERATIONS.contains(operation)) {
            return ResponseEntity.badRequest().build();
        }
        boolean isBalanceUpdated = userService.updateBalance(id, userDTO.getBalance(), operation);
        if (!isBalanceUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
