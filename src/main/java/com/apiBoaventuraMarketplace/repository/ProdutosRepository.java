package com.apiBoaventuraMarketplace.repository;

import com.apiBoaventuraMarketplace.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {

}
