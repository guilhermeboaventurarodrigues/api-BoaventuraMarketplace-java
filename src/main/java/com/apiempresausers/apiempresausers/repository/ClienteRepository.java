package com.apiempresausers.apiempresausers.repository;

import com.apiempresausers.apiempresausers.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}