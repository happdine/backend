package com.bosch.happdine.repositories;

import com.bosch.happdine.models.VotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<VotesModel, UUID> {

        // Seleciona o id do restaurante de cada linha da tabela de votos, conta essas linhas e agrupa, depois ordena em ordem decrescente (do maior para o menor)
        @Query("SELECT v.restauranteModelVotes.id, COUNT(v) as voteCount " +
                "FROM VotesModel v " +
                "WHERE v.dataRegistro = :data " +
                "GROUP BY v.restauranteModelVotes.id " +
                "ORDER BY voteCount DESC")
        List<Object[]> findTopVotedRestaurante(@Param("data") LocalDate data); // Transforma em uma lista

        // Método para encontrar votos por restaurante e data
        @Query("SELECT v FROM VotesModel v WHERE v.restauranteModelVotes.id = :restauranteId AND v.dataRegistro = :dataRegistro")
        List<VotesModel> findVotesByRestaurantAndDate(@Param("restauranteId") Long restauranteId, @Param("dataRegistro") LocalDate dataRegistro);
}
