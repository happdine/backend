package com.bosch.happdine.repositories;

import com.bosch.happdine.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteModel, UUID> {
    RestauranteModel findByNome(String nome);
}
