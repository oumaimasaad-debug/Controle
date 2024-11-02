package com.example.ctrl.dao;
import com.example.ctrl.entities.ProdPromo;
import com.example.ctrl.entities.ProdPromoTable;
import com.example.ctrl.entities.Produit;
import com.example.ctrl.entities.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdPromoRepository extends CrudRepository<ProdPromoTable, ProdPromo>{
List<ProdPromoTable> findAllByProduit(Produit produit);
List<ProdPromoTable> findByPromotion(Promotion promotion);
ProdPromoTable findByPk(ProdPromo pk);
}
