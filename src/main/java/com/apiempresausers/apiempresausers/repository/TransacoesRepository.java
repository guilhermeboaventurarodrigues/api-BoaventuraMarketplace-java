package com.apiempresausers.apiempresausers.repository;

import com.apiempresausers.apiempresausers.entity.TransacoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends JpaRepository<TransacoesEntity, Long> {
}