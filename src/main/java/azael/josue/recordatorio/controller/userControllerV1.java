package azael.josue.recordatorio.controller;

import azael.josue.recordatorio.model.user;
import azael.josue.recordatorio.repository.userRepository;
import azael.josue.recordatorio.service.userService;
import azael.josue.recordatorio.exception.NotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class userControllerV1 {

    private final userService userService;
    private final userRepository userRepository;

    public userControllerV1(userService userService, userRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<user> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public user getUserById(@PathVariable @Positive Long id) {
        return userService.getById(id)
                .orElseThrow(() -> new NotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    @PostMapping
    public user createUser(@RequestBody @Valid user user) {
        // Hashear la contraseña antes de guardar
        String rawPassword = user.getPasswordHash(); // En este caso se envía como campo passwordHash
        user.setPasswordHash(hashPassword(rawPassword));
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user updatedUser) {
        user existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        String nuevaPassword = updatedUser.getPasswordHash();
        if (nuevaPassword != null && !nuevaPassword.isBlank()) {
            existingUser.setPasswordHash(hashPassword(nuevaPassword));
        }

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @Positive Long id) {
        userService.deleteById(id);
    }

    // Método privado para hashear la contraseña con SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}