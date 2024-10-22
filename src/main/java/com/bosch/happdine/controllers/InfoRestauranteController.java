package com.bosch.happdine.controllers;

import com.bosch.happdine.dtos.InfoRestauranteRecordDto;
import com.bosch.happdine.models.InfoRestauranteModel;
import com.bosch.happdine.repositories.InfoRestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/infoRestaurante")
public class InfoRestauranteController {

    @Autowired
    private InfoRestauranteRepository infoRestauranteRepository;

    @GetMapping
    public List<InfoRestauranteModel> getAllInfos() {
        return infoRestauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoRestauranteModel> getInfoById(@PathVariable UUID id) {
        Optional<InfoRestauranteModel> info = infoRestauranteRepository.findById(id);
        if (info.isPresent()) {
            return ResponseEntity.ok(info.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public InfoRestauranteModel createInfoRestaurante(@RequestBody @Valid InfoRestauranteRecordDto infoDto) {
        InfoRestauranteModel infoRestaurante = new InfoRestauranteModel();
        infoRestaurante.setIdInformacoesRestaurante(UUID.randomUUID()); // Manually set the UUID
        infoRestaurante.setHorarioFuncionamento(infoDto.horario_funcionamento());
        infoRestaurante.setLocalizacao(infoDto.localizacao());
        infoRestaurante.setRefeicoesOferecidas(infoDto.refeicoes_oferecidas());
        return infoRestauranteRepository.save(infoRestaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoRestauranteModel> updateInfoRestaurante(@PathVariable UUID id, @RequestBody @Valid InfoRestauranteRecordDto infoDto) {
        Optional<InfoRestauranteModel> existingInfo = infoRestauranteRepository.findById(id);
        if (existingInfo.isPresent()) {
            InfoRestauranteModel infoRestaurante = existingInfo.get();
            infoRestaurante.setHorarioFuncionamento(infoDto.horario_funcionamento());
            infoRestaurante.setLocalizacao(infoDto.localizacao());
            infoRestaurante.setRefeicoesOferecidas(infoDto.refeicoes_oferecidas());
            return ResponseEntity.ok(infoRestauranteRepository.save(infoRestaurante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfoRestaurante(@PathVariable UUID id) {
        Optional<InfoRestauranteModel> existingInfo = infoRestauranteRepository.findById(id);
        if (existingInfo.isPresent()) {
            infoRestauranteRepository.delete(existingInfo.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
