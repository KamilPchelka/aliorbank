package dev.kamilpchelka.techtrial.aliorbank.controller;

import dev.kamilpchelka.techtrial.aliorbank.dto.UserDTO;
import dev.kamilpchelka.techtrial.aliorbank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

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


}
