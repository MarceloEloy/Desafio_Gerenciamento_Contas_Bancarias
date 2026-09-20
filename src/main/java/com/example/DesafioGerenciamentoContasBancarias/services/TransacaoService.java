package com.example.DesafioGerenciamentoContasBancarias.services;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.TransacaoDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoTransacao;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.ContaRepository;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final ContaRepository contaRepository;

    private final ContaService contaService;

    public ResponseEntity adicionarTransacao(TransacaoDTO dto) throws URISyntaxException {

        BigDecimal limite = BigDecimal.valueOf(1000.0);

        Transacao transacao = new Transacao(dto);

        Conta destinatario = contaRepository.findById(dto.getDestinatario()).get();

        if (transacao.getTipo().equals(TipoTransacao.DEPOSITO)){
            destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));
            contaService.realizarOperacao(destinatario);
        } else if (transacao.getTipo().equals(TipoTransacao.SAQUE) && destinatario.getTipo().equals(TipoConta.CONTA_CORRENTE)) {
            if (destinatario.getSaldo().add(limite).intValue() > dto.getValor().intValue()) {
                destinatario.setSaldo(destinatario.getSaldo().subtract(dto.getValor()));
                contaService.realizarOperacao(destinatario);
            }else {
                return ResponseEntity.badRequest().body("Valor da transação não pode exceder {saldo: " + destinatario.getSaldo() + " + limite: " + limite + "}");
            }
        } else if (transacao.getTipo().equals(TipoTransacao.SAQUE) && destinatario.getTipo().equals(TipoConta.CONTA_POUPANCA)) {
            if (destinatario.getSaldo().intValue() > dto.getValor().intValue()){
                destinatario.setSaldo(destinatario.getSaldo().subtract(dto.getValor()));
                contaService.realizarOperacao(destinatario);
            }else {
                return ResponseEntity.badRequest().body("Valor da transação não pode exceder {saldo: " + destinatario.getSaldo() + "}");
            }
        };


        transacao.setDestinatario(destinatario);
        transacao.setData(Timestamp.valueOf(LocalDateTime.now()));

        transacao = transacaoRepository.save(transacao);

        URI uri = new URI("/transacao/" + transacao.getId());

        return ResponseEntity.created(uri).body(transacao);

    }

    public ResponseEntity<Transacao> findTransacaoById(Long id){
        return ResponseEntity.ok(transacaoRepository.findById(id).get());
    }

}
