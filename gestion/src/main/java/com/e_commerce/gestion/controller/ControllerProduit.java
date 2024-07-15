package com.e_commerce.gestion.controller;

import com.e_commerce.gestion.model.Produit;
import com.e_commerce.gestion.service.ServiceProduit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Data
public class ControllerProduit {

    @Autowired
    private ServiceProduit productService;

    @GetMapping
    public List<Produit> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Produit> createProduct(@RequestBody Produit product) {
        Produit createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

}
