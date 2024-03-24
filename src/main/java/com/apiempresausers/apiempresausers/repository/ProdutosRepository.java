package com.apiempresausers.apiempresausers.repository;

import com.apiempresausers.apiempresausers.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {

}
