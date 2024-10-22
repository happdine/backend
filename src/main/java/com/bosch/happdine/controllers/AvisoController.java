package com.bosch.happdine.controllers;

import com.bosch.happdine.models.AvisoModel;
import com.bosch.happdine.models.Categoria;
import com.bosch.happdine.services.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("happdine/api/avisos")
public class AvisoController {
    @Autowired
    private AvisoService avisoService;

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadAviso(
            @RequestParam("recado") String recado,
            @RequestParam("timestampp") String timestampp,
            @RequestParam("categoria") Integer categoria,
            @RequestParam("imagem") MultipartFile imagem) {
        try {
            Categoria catEnum = Categoria.fromValue(categoria);
            avisoService.salvarAviso(recado, timestampp, catEnum, imagem);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getAvisoDetails(@PathVariable Long id) {
        Optional<AvisoModel> avisoOptional = avisoService.buscarAviso(id);
        if (avisoOptional.isPresent()) {
            AvisoModel aviso = avisoOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("recado", aviso.getRecado());
            response.put("timestamp", aviso.getTimestampp());
            response.put("imagemUrl", "/happdine/api/avisos/" + id + "/image"); // Alterar aqui

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/image") // Ajuste aqui para o mapeamento correto
    public ResponseEntity<InputStreamResource> getImage(@PathVariable Long id) {
        Optional<AvisoModel> avisoOptional = avisoService.buscarAviso(id);
        if (avisoOptional.isPresent()) {
            AvisoModel aviso = avisoOptional.get();
            if (aviso.getImagem() != null) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(aviso.getImagem());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                headers.setContentDispositionFormData("attachment", "imagem.jpg");

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(new InputStreamResource(inputStream));
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}