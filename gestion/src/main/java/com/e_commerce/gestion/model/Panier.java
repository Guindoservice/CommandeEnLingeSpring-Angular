package com.e_commerce.gestion.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId; // Utiliser pour identifier l'utilisateur/visiteur
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ArticlePanier> items = new ArrayList<>();

}
