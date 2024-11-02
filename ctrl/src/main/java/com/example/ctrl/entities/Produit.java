package com.example.ctrl.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Produit{
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id;
        @NotBlank(message = "Name is mandatory")
        private String name;
        private Double prix;
        private String description;

        public Produit() {
            super();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description ;
        }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

}

