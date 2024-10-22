package com.bosch.happdine.repositories;

import com.bosch.happdine.models.CardapioModel;
import com.bosch.happdine.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioModel, UUID> {
    List<CardapioModel> findByRestauranteModel(RestauranteModel restauranteModel);
    Optional<CardapioModel> findByRestauranteModelAndData(RestauranteModel restauranteModel, String data);

}
