package com.bosch.happdine.controllers;

import com.bosch.happdine.dtos.ComentDTO;
import com.bosch.happdine.models.ComentModel;
import com.bosch.happdine.services.ComentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentarios")
public class ComentController {
    @Autowired
    private ComentServices service;

    @GetMapping(value="/restaurante", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComentModel>> findAllByRestaurante() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByRestaurante());
    }
    @GetMapping(value="/website", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComentModel>>findAllByWebsite() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByWebSite());
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody @Valid ComentDTO coment) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComent((coment)));
    }
}
