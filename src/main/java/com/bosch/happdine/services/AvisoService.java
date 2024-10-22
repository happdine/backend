package com.bosch.happdine.services;

import com.bosch.happdine.models.AvisoModel;

import com.bosch.happdine.models.Categoria;
import com.bosch.happdine.repositories.AvisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public AvisoModel salvarAviso(String recado, String timestampp, Categoria categoria, MultipartFile file) throws Exception {
        AvisoModel aviso = new AvisoModel();
        aviso.setRecado(recado);
        aviso.setTimestampp(timestampp);
        aviso.setCategoria(categoria);  // Categoria já é um enum, então você pode setar diretamente.

        if (file != null && !file.isEmpty()) {
            aviso.setImagem(file.getBytes());  // Salva a imagem como byte[]
        }

        return avisoRepository.save(aviso);  // Salva o aviso no banco de dados
    }

    public Optional<AvisoModel> buscarAviso(Long id) {
        return avisoRepository.findById(id);  // Busca o aviso pelo ID
    }

    public void salvarAviso(String recado, String timestampp, Categoria categoria, String imagem) {
    }
}