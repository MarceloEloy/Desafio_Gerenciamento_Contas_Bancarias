package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.ContaDTO;
import com.example.DesafioGerenciamentoContasBancarias.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping(path = "/add")
    public ResponseEntity<Conta> addConta(@RequestBody ContaDTO dto) throws URISyntaxException {

        return contaService.adicionarConta(dto);

    };

    @GetMapping(path = "/{id}")
    public ResponseEntity<Conta> findContaById(@PathVariable Long id){

        return contaService.findContaById(id);

    }

}
