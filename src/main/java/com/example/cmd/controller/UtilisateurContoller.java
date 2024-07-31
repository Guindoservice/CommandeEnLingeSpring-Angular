package com.example.cmd.controller;

import com.example.cmd.DTO.UserDTO;
import com.example.cmd.DTO.UtilisateurUpdateDto;
import com.example.cmd.model.Utilisateur;
import com.example.cmd.repository.UtilisateurRepository;
import com.example.cmd.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.cmd.model.MyHttpResponse.response;


@RestController
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UtilisateurContoller {
    @Autowired
    private UtilisateurService utilisateurService;

    private AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurContoller(AuthenticationManager authenticationManager, UtilisateurRepository utilisateurRepository) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("login")
    public ResponseEntity<Object> seConnecter(@RequestBody UserDTO userDTO) {
        Optional<Utilisateur> user;
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );
        if (auth.isAuthenticated()) {
            user = utilisateurRepository.findByUsername(auth.getName());
            Utilisateur userInDB = user.get();
            userDTO.setUsername(userInDB.getUsername());

            userDTO.setNom(userInDB.getNom());
            userDTO.setPrenom(userInDB.getPrenom());


            return response(HttpStatus.OK, "Authentifié avec succès !", userDTO);
        }
        return response(HttpStatus.UNAUTHORIZED, "Echec d'authentificaton !", "okpk");
    }
    @GetMapping("/current")
    public ResponseEntity<Object> getCurrentUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return response(HttpStatus.UNAUTHORIZED, "Utilisateur non authentifié !", null);
        }

        Optional<Utilisateur> user = utilisateurRepository.findByUsername(userDetails.getUsername());

        if (user.isPresent()) {
            Utilisateur userInDB = user.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userInDB.getUsername());
            userDTO.setNom(userInDB.getNom());
            userDTO.setPrenom(userInDB.getPrenom());
            userDTO.setPassword(userInDB.getMotDePasse());

            return ResponseEntity.ok(userDTO);
        } else {
            return response(HttpStatus.NOT_FOUND, "Utilisateur non trouvé !", null);
        }
    }
    @PutMapping("/modifierutilisateur")
    public ResponseEntity<String> modifierUtilisateur(
            @RequestBody UtilisateurUpdateDto utilisateurUpdateDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            // Retourner une réponse HTTP 401 Unauthorized si userDetails est null
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

        // Extraire le nom d'utilisateur de userDetails
        String username = userDetails.getUsername();

        try {
            // Appeler le service pour modifier les informations de l'utilisateur
            utilisateurService.modifierUtilisateur(
                    username,
                    utilisateurUpdateDto.getNouveauNom(),
                    utilisateurUpdateDto.getNouveauPrenom()

            );

            // Retourner une réponse HTTP 200 OK si la modification réussit
            return ResponseEntity.ok("Utilisateur modifié avec succès");

        } catch (Exception e) {
            // Gérer les exceptions et retourner une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }
}

