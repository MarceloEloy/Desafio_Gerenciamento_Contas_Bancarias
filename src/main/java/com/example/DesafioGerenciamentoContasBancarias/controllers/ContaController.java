package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.ContaDTO;
import com.example.DesafioGerenciamentoContasBancarias.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Operation(description = "Operação para gerar conta")
    @PostMapping(path = "/add")
    public ResponseEntity addConta(@RequestBody @Valid ContaDTO dto) throws URISyntaxException {

        return contaService.adicionarConta(dto);

    };

    @Operation(description = "Operação para buscar um contas passando seu id no caminho do endpoint")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Conta> findContaById(@PathVariable Long id){

        return contaService.findContaById(id);

    };

    @Operation(description = "Operação para buscar um correntista passando o id de seu correntista no caminho do endpoint")
    @GetMapping(path = "/correntista/{id}")
    public ResponseEntity<List<Conta>> findAllByCorrentistaId(@PathVariable Long id){

        return contaService.findAllContasByCorrentistaId(id);

    }

}
