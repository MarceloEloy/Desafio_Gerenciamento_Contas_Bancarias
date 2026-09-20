package com.example.DesafioGerenciamentoContasBancarias.controllers;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.TransacaoDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "Operação para gerar transação em uma única conta")
    @PostMapping(path = "/add/solo")
    public ResponseEntity<Transacao> addTransacao(@RequestBody TransacaoDTO dto) throws URISyntaxException {

        return transacaoService.adicionarTransacao(dto);

    }

    @Operation(description = "Operação para gerar transação entre duas contas")
    @PostMapping(path = "/add/duo")
    public ResponseEntity<Transacao> addTransacaoDuo(@RequestBody TransacaoDTO dto) throws URISyntaxException {

        return transacaoService.adicionarTransacaoEntreContas(dto);

    }

    @Operation(description = "Operação para gerar transação recebendo uma taxa para aplicar o rendimento em contas do tipo 'CONTA_POUPANCA'")
    @PostMapping(path = "/add/rendimento/{taxa}")
    public ResponseEntity<Transacao> addRendimentoTransacao(@RequestBody TransacaoDTO dto, @PathVariable Double taxa) throws URISyntaxException {

        return transacaoService.aplicarRendimentoMensal(dto, taxa);

    }

    @Operation(description = "Operação para gerar transação de cobrança de uma taxa de juros sobre uma conta com saldo negativo")
    @PostMapping(path = "/add/juros")
    public ResponseEntity addCobrancaDeJuros(@RequestParam Long destinatario, @RequestParam Double taxa) throws URISyntaxException {

        return transacaoService.aplicarJuros(destinatario, taxa);

    }

    @Operation(description = "Operação buscar transações passando o id do destinatario da transação")
    @GetMapping(path = "/destinatario/{id}")
    public ResponseEntity<List<Transacao>> getAllByDestinatario(@PathVariable Long id){

        return transacaoService.findTransacaoByDestinatario(id);

    }

    @Operation(description = "Operação buscar transações passando o id do remetente da transação")
    @GetMapping(path = "/remetente/{id}")
    public ResponseEntity<List<Transacao>> getAllByRemetente(@PathVariable Long id){

        return transacaoService.findTransacaoByRemetente(id);

    }

    @Operation(description = "Operação buscar uma transação passando o id da transação")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable Long id){

        return transacaoService.findTransacaoById(id);

    }

}
