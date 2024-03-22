package com.apiempresausers.apiempresausers.repository;

import com.apiempresausers.apiempresausers.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
}