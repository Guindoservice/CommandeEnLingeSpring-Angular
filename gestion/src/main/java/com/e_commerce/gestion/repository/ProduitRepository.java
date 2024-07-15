package com.e_commerce.gestion.repository;

import com.e_commerce.gestion.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
