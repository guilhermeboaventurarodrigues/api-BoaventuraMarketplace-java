package com.apiBoaventuraMarketplace.repository;

import com.apiBoaventuraMarketplace.entity.TransacoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends JpaRepository<TransacoesEntity, Long> {
}