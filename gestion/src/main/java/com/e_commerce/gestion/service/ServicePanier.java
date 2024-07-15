package com.e_commerce.gestion.service;


import com.e_commerce.gestion.model.ArticlePanier;
import com.e_commerce.gestion.model.Panier;
import com.e_commerce.gestion.model.Produit;
import com.e_commerce.gestion.repository.PanierRepository;
import com.e_commerce.gestion.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicePanier {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ProduitRepository produitRepository;

    // chercher un panier associé à un identifiant de session spécifique.
    public Panier getCartBySessionId(String sessionId) {
        return panierRepository.findBySessionId(sessionId)
                .orElse(new Panier(null, sessionId, new ArrayList<>()));
    }

    // ajouter un produit au panier.
    public Panier addProductToCart(String sessionId, Long productId, int quantity) {
        Panier cart = getCartBySessionId(sessionId);
        Produit product = produitRepository.findById(productId).orElseThrow();
        ArticlePanier cartItem = new ArticlePanier(null, product, quantity);
        cart.getItems().add(cartItem);
        return panierRepository.save(cart);
    }

    //supprimer un produit du panier.
    public Panier removeProductFromCart(String sessionId, Long productId) {
        Panier cart = getCartBySessionId(sessionId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return panierRepository.save(cart);
    }
}
