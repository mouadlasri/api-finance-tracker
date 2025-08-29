package org.financetracker.apifinancetracker.user;

import org.financetracker.apifinancetracker.user.dto.CreateUserRequest;
import org.financetracker.apifinancetracker.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        // pass DTO to service which handles the business logic
        User newUser = userService.createUser(request);

        UserResponse response = new UserResponse(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
