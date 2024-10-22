package com.bosch.happdine.controllers;

import com.bosch.happdine.dtos.VotesRecordDto;
import com.bosch.happdine.models.RestauranteModel;
import com.bosch.happdine.models.VotesModel;
import com.bosch.happdine.repositories.RestauranteRepository;
import com.bosch.happdine.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/votos")
public class VotesController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    // POST - Add a new vote
    @PostMapping
    public ResponseEntity<VotesModel> createVote(@RequestBody VotesRecordDto votesDto) {
        VotesModel newVote = new VotesModel();
        newVote.setDataRegistro(votesDto.getDataRegistro());

        // Buscar o restaurante correspondente pelo ID
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(votesDto.getRestauranteId());

        if (restauranteOptional.isPresent()) {
            // Associar o restaurante encontrado ao voto
            newVote.setRestauranteModelId(restauranteOptional.get());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Restaurante não encontrado
        }

        // Save the vote to the repository
        VotesModel savedVote = voteRepository.save(newVote);
        return new ResponseEntity<>(savedVote, HttpStatus.CREATED);
    }


    // GET - Get the votes for a specific date
    @GetMapping("/{data}")
    public ResponseEntity<List<Object[]>> getTopVotedRestaurante(@PathVariable("data") String data) {
        try {
            LocalDate voteDate = LocalDate.parse(data);
            List<Object[]> topVoted = voteRepository.findTopVotedRestaurante(voteDate);
            if (topVoted.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(topVoted, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Optional: GET to retrieve a specific vote by ID
    @GetMapping("/vote/{id}")
    public ResponseEntity<VotesModel> getVoteById(@PathVariable("id") UUID id) {
        Optional<VotesModel> vote = voteRepository.findById(id);
        return vote.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
