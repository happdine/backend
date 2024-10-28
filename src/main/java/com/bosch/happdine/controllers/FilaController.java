package com.bosch.happdine.controllers;

import com.bosch.happdine.dtos.FilaRecordDto;
import com.bosch.happdine.models.FilaModel;
import com.bosch.happdine.models.RestauranteModel;
import com.bosch.happdine.repositories.FilaRepository;
import com.bosch.happdine.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/fila")
public class FilaController {

    @Autowired
    private FilaRepository filaRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    // POST - Create a new fila
    @PostMapping
    public ResponseEntity<FilaModel> createFila(@RequestBody FilaRecordDto filaDto) {
        // Verifica se o restaurante existe
        Optional<RestauranteModel> restauranteOptional = restauranteRepository.findById(filaDto.restauranteId());
        if (restauranteOptional.isPresent()) {
            RestauranteModel restauranteModel = restauranteOptional.get();

            // Cria a fila e associa ao restaurante
            FilaModel filaModel = new FilaModel();
            filaModel.setAtivado(filaDto.isAtivado());
            filaModel.setLocalizacao(filaDto.localizacao());
            filaModel.setRestauranteIdModel(restauranteModel);

            FilaModel savedFila = filaRepository.save(filaModel);
            return new ResponseEntity<>(savedFila, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Restaurante não encontrado
        }
    }


    // GET - Get all filas
    @GetMapping
    public ResponseEntity<List<FilaModel>> getAllFilas() {
        List<FilaModel> filas = filaRepository.findAll();
        return new ResponseEntity<>(filas, HttpStatus.OK);
    }

    // GET - Get a fila by ID
    @GetMapping("/{id}")
    public ResponseEntity<FilaModel> getFilaById(@PathVariable("id") UUID id) {
        Optional<FilaModel> fila = filaRepository.findById(id);
        return fila.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT - Update a fila by ID
    @PutMapping("/{id}")
    public ResponseEntity<FilaModel> updateFila(@PathVariable("id") UUID id, @RequestBody FilaModel filaDetails) {
        Optional<FilaModel> filaOptional = filaRepository.findById(id);

        if (filaOptional.isPresent()) {
            FilaModel filaToUpdate = filaOptional.get();
            filaToUpdate.setAtivado(filaDetails.isAtivado());
            filaToUpdate.setLocalizacao(filaDetails.getLocalizacao());
            filaToUpdate.setRestauranteIdModel(filaDetails.getRestauranteIdModel());

            FilaModel updatedFila = filaRepository.save(filaToUpdate);
            return new ResponseEntity<>(updatedFila, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete a fila by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFila(@PathVariable("id") UUID id) {
        Optional<FilaModel> fila = filaRepository.findById(id);

        if (fila.isPresent()) {
            filaRepository.delete(fila.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
