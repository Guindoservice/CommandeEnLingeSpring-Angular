package com.e_commerce.gestion.controller;

import com.e_commerce.gestion.model.Avis;
import com.e_commerce.gestion.service.ServiceAvis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avi")
public class ControllerAvis {

    @Autowired
    private ServiceAvis serviceAvis;

    @GetMapping("/{productId}")
    public List<Avis> getReviewsByProductId(@PathVariable Long productId) {
        return serviceAvis.getReviewsByProductId(productId);
    }

    @PostMapping("/add")
    public Avis addReview(@RequestParam Long productId, @RequestParam String customerName, @RequestParam String comment, @RequestParam int rating) {
        return serviceAvis.addReview(productId, customerName, comment, rating);
    }
}
