package com.bosch.happdine.repositories;

import com.bosch.happdine.models.AvisoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoRepository extends JpaRepository<AvisoModel, Long> {
}
