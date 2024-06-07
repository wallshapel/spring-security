package com.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // Funciona si en la configuración de spring security está la anotación @EnableMethodSecurity. No deja pasar a nadie
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()") // Se le da más prioridad a esta preautorización por encima de la que está a nivel de clase
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('CREATE')") // para acceder a este endpoint hay que tener permisos de Create y obviamente estar autenticado
    public String helloSecured() { return "Hello world secured"; }

    @GetMapping("/hello2") // Se le aplicará el denyAll()
    public String hello2() { return "hello world 2"; }

}
