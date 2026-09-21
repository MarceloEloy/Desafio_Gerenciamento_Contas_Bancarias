package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.CorrentistaDTO;
import com.example.DesafioGerenciamentoContasBancarias.services.CorrentistaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/correntista")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @Operation(description = "Operação para gerar correntistas")
    @PostMapping(path = "/add")
    public ResponseEntity<Correntista> addCorrentista(@RequestBody @Valid CorrentistaDTO dto) throws URISyntaxException, MalformedURLException {

        return correntistaService.adicionarCorrentista(dto);

    };

    @Operation(description = "Operação para buscar um correntistas passando seu id no caminho do endpoint")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Correntista> findCorrentistaById(@PathVariable Long id){

        return correntistaService.findCorrentistaById(id);

    };

    @Operation(description = "Operação para buscar um correntistas passando seu nome no caminho do endpoint")
    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Correntista>> findAllCorrentistaById(@PathVariable String nome){

        return correntistaService.findCorrentistaByName(nome);

    }

}
