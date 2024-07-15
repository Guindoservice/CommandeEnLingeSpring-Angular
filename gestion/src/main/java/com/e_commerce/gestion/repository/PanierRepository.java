package com.e_commerce.gestion.repository;

import com.e_commerce.gestion.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanierRepository  extends JpaRepository<Panier, Long> {
    Optional<Panier> findBySessionId(String sessionId);
}
