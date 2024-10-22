package com.bosch.happdine.controllers;

import com.bosch.happdine.models.RestauranteModel;
import com.bosch.happdine.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @GetMapping("/restaurantes")
    public ResponseEntity<List<RestauranteModel>> getAllRestaurants() {
        List<RestauranteModel> restaurantes = restauranteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }



}
