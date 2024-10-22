package com.bosch.happdine.repositories;

import com.bosch.happdine.models.FilaModel;
import com.bosch.happdine.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilaRepository extends JpaRepository<FilaModel, UUID> {
    List<FilaModel> findByRestauranteIdModel(RestauranteModel restauranteModel);
    FilaModel findByLocalizacao(String localizacao);
}

