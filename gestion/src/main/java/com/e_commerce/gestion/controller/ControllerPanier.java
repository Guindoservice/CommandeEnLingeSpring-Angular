package com.e_commerce.gestion.controller;


import com.e_commerce.gestion.model.Panier;
import com.e_commerce.gestion.service.ServicePanier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
public class ControllerPanier {
    @Autowired
    private ServicePanier servicePanier;

    @GetMapping
    public Panier getCart(@RequestParam String sessionId) {
        return servicePanier.getCartBySessionId(sessionId);
    }

    @PostMapping("/add")
    public Panier addProductToCart(@RequestParam String sessionId, @RequestParam Long productId, @RequestParam int quantity) {
        return servicePanier.addProductToCart(sessionId, productId, quantity);
    }

    @DeleteMapping("/remove")
    public Panier removeProductFromCart(@RequestParam String sessionId, @RequestParam Long productId) {
        return servicePanier.removeProductFromCart(sessionId, productId);
    }
}
