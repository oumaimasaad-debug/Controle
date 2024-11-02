package com.example.ctrl.entities;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
@Embeddable
public class ProdPromo implements Serializable {
    private int produit;
    private int promotion;
    public ProdPromo() {}

    public ProdPromo(int produit, int promo) {
        this.produit = produit;
        this.promotion = promo;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promo) {
        this.promotion = promo;
    }
}
