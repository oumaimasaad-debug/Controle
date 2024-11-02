package com.example.ctrl.controller;

import com.example.ctrl.dao.ProdPromoRepository;
import com.example.ctrl.dao.ProduitRepository;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
private ProdPromoRepository repo;
    @GetMapping("/")
    public String redirectToProduits() {
        return "redirect:/produits"; // Redirige vers /produits
    }
    @GetMapping("/produits")
    public String showAddProduitList(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produits"; // Nom de la vue pour ajouter un produit
    }
    // Afficher le formulaire d'ajout de produit
    @GetMapping("/addProduct")
    public String showAddProductForm(@ModelAttribute("produit") Produit produit, Model model,Promotion promotion) {
        return "add-product"; // Nom de la vue pour ajouter un produit
    }

    // Ajouter un produit après soumission du formulaire
    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("produit") Produit produit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product"; // Retourne au formulaire en cas d'erreur
        }
        produitRepository.save(produit);// Sauvegarde le produit

        return "produits";
    }// Redirige vers la page produits après ajout
    @PostMapping("/addProductPromo")
    public String addProduct(@RequestParam int produitId, @RequestParam int promotionId, Model model) {

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion introuvable"));
        ProdPromo pk = new ProdPromo();
        pk.setProduit(produitId);
        pk.setPromotion(promotionId);
        model.addAttribute("pk",pk);
        // Créer l'objet ProdPromo et l'assigner
        ProdPromoTable prodPromo = new ProdPromoTable();
        prodPromo.setPk(pk);
        prodPromo.setProduit(produit);
        prodPromo.setPromotion(promotion);
        model.addAttribute("promotionsassociés", promotionRepository.findAll());
        ProdPromoTable pp = new ProdPromoTable();
        pp.setPk(pk);
        repo.save(pp);
        return "produits";
    }

    @GetMapping("/showprodpromo/{id}")
    public String showprodpromo(@PathVariable("id") int id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id: " + id));
        List<ProdPromoTable> promo = repo.findAllByProduit(produit);

        model.addAttribute("Liste", promo);
        return "promotions"; // Redirige vers la page produits après suppression
    }
    // Afficher le formulaire de mise à jour d'un produit
    @GetMapping("/updateProduct/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id: " + id));
        model.addAttribute("produit", produit);
        return "edit"; // Nom de la vue pour mettre à jour un produit
    }

    // Mettre à jour un produit après soumission du formulaire
    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") int id, @Valid Produit produit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            produit.setId(id);
            return "edit"; // Retourne au formulaire de mise à jour en cas d'erreur
        }

        produitRepository.save(produit);
        model.addAttribute("produits", produitRepository.findAll());
        return "produits"; // Redirige vers la page produits après mise à jour
    }

    // Supprimer un produit
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id: " + id));
        produitRepository.delete(produit);
        model.addAttribute("produits", produitRepository.findAll());
        return "produits"; // Redirige vers la page produits après suppression
    }

}


