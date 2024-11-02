package com.example.ctrl.dao;

import com.example.ctrl.entities.Produit;
import com.example.ctrl.entities.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Integer> {
}
