package com.example.ctrl.controller;

import com.example.ctrl.dao.ProdPromoRepository;
import com.example.ctrl.dao.PromotionRepository;
import com.example.ctrl.entities.ProdPromo;
import com.example.ctrl.entities.ProdPromoTable;
import com.example.ctrl.entities.Produit;
import com.example.ctrl.entities.Promotion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    ProdPromoRepository prodPromoRepository;
    // Afficher la liste des produits
    @GetMapping("/Promotionstable")
    public String showPromotionList(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        return "Promotionstable"; // Nom de la vue pour afficher la liste des produits
    }
    // Afficher le formulaire d'ajout de promotion
    @GetMapping("/addPromotion")
    public String showAddPromotionForm(Promotion promotion) {
        return "add-promotion"; // Nom de la vue pour ajouter un produit
    }
    // Ajouter une promotion après soumission du formulaire
    @PostMapping("/addPromotion")
    public String addPromotion(@Valid Promotion promotion,BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-promotion"; // Retourne au formulaire en cas d'erreur
        }

        promotionRepository.save(promotion); // Sauvegarde la promotion
        model.addAttribute("promotions", promotionRepository.findAll()); // Met à jour la liste des promotions
        return "produits"; // Redirige vers la page de la liste des promotions
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid promotion Id:" + id));
        model.addAttribute("promotion", promotion);
        model.addAttribute("promotions", promotionRepository.findAll());
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String updatePromotion(@PathVariable("id") int id, @Valid Promotion promotion, BindingResult result, Model model) {
        if (result.hasErrors()) {
            promotion.setId(id);
            return"edit";
        }

        promotionRepository.save(promotion);
        model.addAttribute("promotionss", promotionRepository.findAll());
        return "promotions";
    }

    @GetMapping("/deletePromotion")
    public String deletePromotion(@Valid @ModelAttribute("promotion") ProdPromoTable promotion, Model model) {

        prodPromoRepository.delete(promotion);
        model.addAttribute("promotions", promotionRepository.findAll());
        return "Promotionstable";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") int id, Model model) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid promotion Id:" + id));
        promotionRepository.delete(promotion);
        model.addAttribute("promotions", promotionRepository.findAll());
        return"Promotionstable";
    }

}

