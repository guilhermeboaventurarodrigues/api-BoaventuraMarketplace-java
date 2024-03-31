package com.apiBoaventuraMarketplace.repository;

import com.apiBoaventuraMarketplace.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

}