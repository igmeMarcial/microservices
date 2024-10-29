package com.example.ms_recomm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recommend")
public class RecommController {

    private final UserService userService;
    private final JwtService jwtService;

    public RecommController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
    @GetMapping
    public String getRecommendation() {
        return "Recomendación: Recomendación operativa";
    }
    @GetMapping("users/{id}")
    public String getUserFromUserService(HttpServletRequest req, @PathVariable Long id) {
        String token = req.getHeader("Authorization");
        //Quitar el Bearer al token
        token = token.substring(7);      
        String mensaje = "";
        // Validar token para consumir servicio
        boolean isValid = jwtService.isTokenValid(token);
        if (isValid) {
            mensaje = "USTED ESTA AUTORIZADO";
        } else {
            mensaje = "TOKEN NO VALIDO";
        }

        return mensaje;

    }
}
