package azael.josue.recordatorio.controller;

import azael.josue.recordatorio.dto.signInRequest;
import azael.josue.recordatorio.model.user;
import azael.josue.recordatorio.repository.userRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v2")
public class userControllerV2 {

    private userRepository userRepository;

    public userControllerV2(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Endpoint para iniciar sesión.
     * Recibe un email y una contraseña, hashea la contraseña y crea un nuevo usuario.
     *
     * @param request Contiene el email y la contraseña del usuario.
     * @return El usuario creado.
     */
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public user signIn(@Valid @RequestBody signInRequest request) {
        // Hasheamos la contraseña usando SHA-256
        String hashedPassword = hashPassword(request.getPassword());

        // Creamos el usuario
        user nuevoUsuario = new user();
        nuevoUsuario.setUsername(request.getEmail().split("@")[0]); // Ejemplo: "azael" de "azael@email.com"
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPasswordHash(hashedPassword);

        // Guardamos el usuario
        return userRepository.save(nuevoUsuario);
    }

    /**
     * Método para hashear la contraseña usando SHA-256.
     *
     * @param password La contraseña a hashear.
     * @return La contraseña hasheada en formato hexadecimal.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    /**
     * Método para convertir un array de bytes a una cadena hexadecimal.
     *
     * @param bytes El array de bytes a convertir.
     * @return La representación hexadecimal del array de bytes.
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}