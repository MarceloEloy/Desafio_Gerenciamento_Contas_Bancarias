package com.example.DesafioGerenciamentoContasBancarias.services;

import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.CorrentistaDTO;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.CorrentistaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CorrentistaService {

    private final CorrentistaRepository correntistaRepository;

    @Transactional
    public ResponseEntity<Correntista> adicionarCorrentista(CorrentistaDTO dto) throws URISyntaxException, MalformedURLException {

        log.info("Inicializando operação de criação de correntista");

        Correntista correntista = new Correntista(dto);

        correntista = correntistaRepository.save(correntista);

        URI uri = new URI("/correntista/" + correntista.getId());

        return ResponseEntity.created(uri).body(correntista);

    }

    public ResponseEntity<Correntista> findCorrentistaById(Long id){

        return ResponseEntity.ok(correntistaRepository.findById(id).get());
    }

    public ResponseEntity<List<Correntista>> findCorrentistaByName(String nome){

        return ResponseEntity.ok(correntistaRepository.findAllByNome(nome));
    }



}
