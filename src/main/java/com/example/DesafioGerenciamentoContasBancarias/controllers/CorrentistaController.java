package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.CorrentistaDTO;
import com.example.DesafioGerenciamentoContasBancarias.services.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/correntista")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @PostMapping(path = "/add")
    public ResponseEntity<Correntista> addCorrentista(@RequestBody @Valid CorrentistaDTO dto) throws URISyntaxException, MalformedURLException {

        return correntistaService.adicionarCorrentista(dto);

    };

    @GetMapping(path = "/{id}")
    public ResponseEntity<Correntista> findCorrentistaById(@PathVariable Long id){

        return correntistaService.findCorrentistaById(id);

    };

}
