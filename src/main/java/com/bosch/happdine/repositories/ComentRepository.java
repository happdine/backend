package com.bosch.happdine.repositories;

import com.bosch.happdine.models.ComentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentRepository extends JpaRepository<ComentModel, Long> {
}
