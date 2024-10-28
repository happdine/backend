package com.bosch.happdine.repositories;

import com.bosch.happdine.models.FilaModel;
import com.bosch.happdine.models.MonitoramentoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MonitoramentoRepository extends JpaRepository<MonitoramentoModel, UUID> {
    List<MonitoramentoModel> findByFilaModel(FilaModel filaModel);
    // Query customizada para encontrar o registro mais recente por localização
    @Query("SELECT m FROM MonitoramentoModel m JOIN m.filaModel f WHERE f.localizacao = :localizacao ORDER BY m.dataRegistro DESC, m.horarioRegistro DESC")
    List<MonitoramentoModel> findLatestByLocation(@Param("localizacao") String localizacao, Pageable pageable);



    @Query("SELECT m FROM MonitoramentoModel m WHERE m.dataRegistro = :data ORDER BY m.horarioRegistro DESC, m.id DESC")
    List<MonitoramentoModel> findAllByDate(@Param("data") LocalDate data);


    // Query para obter dados de monitoramento de uma semana específica, agrupados por hora
    @Query("SELECT m FROM MonitoramentoModel m JOIN m.filaModel f WHERE f.localizacao = :localizacao AND m.dataRegistro = :data ORDER BY m.horarioRegistro DESC, m.id DESC")
    List<MonitoramentoModel> findAllByLocationAndDate(@Param("localizacao") String localizacao, @Param("data") LocalDate data);
}

