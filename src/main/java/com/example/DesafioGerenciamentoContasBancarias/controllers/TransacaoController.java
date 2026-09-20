package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.TransacaoDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping(path = "/add/solo")
    public ResponseEntity<Transacao> addTransacao(@RequestBody TransacaoDTO dto) throws URISyntaxException {

        return transacaoService.adicionarTransacao(dto);

    }

    @PostMapping(path = "/add/duo")
    public ResponseEntity<Transacao> addTransacaoDuo(@RequestBody TransacaoDTO dto) throws URISyntaxException {

        return transacaoService.adicionarTransacaoEntreContas(dto);

    }

    @GetMapping(path = "/destinatario/{id}")
    public ResponseEntity<List<Transacao>> getAllByDestinatario(@PathVariable Long id){

        return transacaoService.findTransacaoByDestinatario(id);

    }

}
