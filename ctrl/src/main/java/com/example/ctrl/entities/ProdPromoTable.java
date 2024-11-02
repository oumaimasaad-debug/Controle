package com.example.ctrl.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class ProdPromoTable {
    @EmbeddedId
    private ProdPromo pk;
   @ManyToOne
    @JoinColumn(name = "produit", insertable = false, updatable = false)
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "promotion", insertable = false, updatable = false)
    private Promotion promotion;


    public ProdPromoTable() {
    }

    public ProdPromoTable(Produit produit) {
        this.produit = produit;
    }

    public ProdPromoTable(ProdPromo pk, Produit produit) {
        this.pk = pk;
        this.produit = produit;
    }

    public ProdPromo getPk() {
        return pk;
    }

    public void setPk(ProdPromo pk) {
        this.pk = pk;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
