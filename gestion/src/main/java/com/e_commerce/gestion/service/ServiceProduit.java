package com.e_commerce.gestion.service;

import com.e_commerce.gestion.model.Produit;
import com.e_commerce.gestion.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduit {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    public Produit createProduct(Produit product) {
        return produitRepository.save(product);
    }

    public Optional<Produit> getProductById(Long id) {
        return produitRepository.findById(id);
    }
}
