package com.example.ms_cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final JwtService jwtService;

    public ClienteController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/info")
    public String getClienteInfo() {
        return "Información del cliente: Cliente operativo";
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserFromUserService(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            boolean isValid = jwtService.isTokenValid(token);
            if (isValid) {
                return ResponseEntity.ok("Usuario autorizado. ID del usuario: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token de autorización no proporcionado o mal formado");
        }
    }
}