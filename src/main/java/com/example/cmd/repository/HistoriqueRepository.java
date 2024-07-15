package com.example.cmd.repository;

import com.example.cmd.model.Action;
import com.example.cmd.model.Historique;
import com.example.cmd.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    List<Historique> findAllBy_date(Date date);
    List<Historique> findAllBy_faitPar(Utilisateur faitPar);
    List<Historique> findAllBy_dateBetween(Date date1, Date date2);
    List<Historique> findAllBy_action(Action action);
}
