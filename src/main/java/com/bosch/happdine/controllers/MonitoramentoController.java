package com.bosch.happdine.controllers;

import com.bosch.happdine.models.FilaModel;
import com.bosch.happdine.models.MonitoramentoModel;
import com.bosch.happdine.repositories.FilaRepository;
import com.bosch.happdine.repositories.MonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/monitoramento")
@CrossOrigin(origins = "http://localhost:5173")
public class MonitoramentoController {  // Renomear para Controller
    @Autowired
    private MonitoramentoRepository monitoramentoRepository;
    @Autowired
    private FilaRepository filaRepository;

    // POST - Adicionar um novo registro de monitoramento
    @PostMapping
    public ResponseEntity<MonitoramentoModel> criarMonitoramento(@RequestBody MonitoramentoModel monitoramento) {
        if (monitoramento.getFilaModel() != null && monitoramento.getFilaModel().getId() != null) {
            Optional<FilaModel> filaOptional = filaRepository.findById(monitoramento.getFilaModel().getId());
            if (filaOptional.isPresent()) {
                monitoramento.setFilaModel(filaOptional.get()); // Use a fila existente
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Fila não encontrada
            }
        }

        MonitoramentoModel monitoramentoSalvo = monitoramentoRepository.save(monitoramento);
        return new ResponseEntity<>(monitoramentoSalvo, HttpStatus.CREATED);
    }


    // GET - Obter o registro mais recente de uma localização específica
    @GetMapping("/ultimo/{localizacao}")
    public ResponseEntity<MonitoramentoModel> obterUltimoPorLocalizacao(@PathVariable("localizacao") String localizacao) {
        List<MonitoramentoModel> ultimosMonitoramentos = monitoramentoRepository.findLatestByLocation(localizacao, PageRequest.of(0, 1));

        if (!ultimosMonitoramentos.isEmpty()) {
            return new ResponseEntity<>(ultimosMonitoramentos.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/pegar/data/{data}")
    public ResponseEntity<List<MonitoramentoModel>> pegarPorData(@PathVariable("data") String data) {
        try {
            LocalDate dataRegistro = LocalDate.parse(data);

            // Busca todos os registros para a data especificada
            List<MonitoramentoModel> monitoramentos = monitoramentoRepository.findAllByDate(dataRegistro);

            if (monitoramentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(monitoramentos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }






}
