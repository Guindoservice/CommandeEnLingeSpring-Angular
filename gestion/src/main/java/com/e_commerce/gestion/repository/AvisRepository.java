package com.e_commerce.gestion.repository;

import com.e_commerce.gestion.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {

    List<Avis> findByProductId(Long productId);
}
