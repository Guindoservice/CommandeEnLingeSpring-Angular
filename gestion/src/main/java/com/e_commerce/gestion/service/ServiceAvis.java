package com.e_commerce.gestion.service;

import com.e_commerce.gestion.model.Avis;
import com.e_commerce.gestion.model.Produit;
import com.e_commerce.gestion.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAvis {

    @Autowired
    private AvisRepository avisRepository;

    // récuperer tous les avis pour un produit donné.
    public List<Avis> getReviewsByProductId(Long productId) {

        return avisRepository.findByProductId(productId);
    }

    //ajouter un nouvel avis pour un produit.
    public Avis addReview(Long productId, String customerName, String comment, int rating) {
        Avis review = new Avis(null, new Produit(productId, null, null, 0, 0), customerName, comment, rating);
        return avisRepository.save(review);
    }
}
