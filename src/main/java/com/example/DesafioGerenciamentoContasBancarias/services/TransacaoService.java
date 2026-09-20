package com.example.DesafioGerenciamentoContasBancarias.services;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.ContaDTO;
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
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final ContaService contaService;

    private BigDecimal limite;

    public ResponseEntity adicionarTransacao(TransacaoDTO dto) throws URISyntaxException {

        this.limite = BigDecimal.valueOf(1000.0);

        Transacao transacao = new Transacao(dto);

        Conta destinatario = contaService.findContaById(dto.getDestinatario()).getBody();

        if (transacao.getTipo().equals(TipoTransacao.DEPOSITO)){

            destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));
            contaService.realizarOperacao(destinatario);

        } else if (transacao.getTipo().equals(TipoTransacao.SAQUE) && destinatario.getTipo().equals(TipoConta.CONTA_CORRENTE)) {
            if (destinatario.getSaldo().add(limite).doubleValue() > dto.getValor().doubleValue()) {

                destinatario.setSaldo(destinatario.getSaldo().subtract(dto.getValor()));
                contaService.realizarOperacao(destinatario);

            }else {
                return ResponseEntity.badRequest().body("Valor da transação não pode exceder {saldo: " + destinatario.getSaldo() + " + limite: " + limite + "}");
            }
        } else if (transacao.getTipo().equals(TipoTransacao.SAQUE) && destinatario.getTipo().equals(TipoConta.CONTA_POUPANCA)) {
            if (destinatario.getSaldo().doubleValue() > dto.getValor().doubleValue()){

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

    public ResponseEntity adicionarTransacaoEntreContas(TransacaoDTO dto) throws URISyntaxException {

        this.limite = BigDecimal.valueOf(1000.0);

        Transacao transacao = new Transacao(dto);

        Conta destinatario = contaService.findContaById(dto.getDestinatario()).getBody();
        Conta remetente = contaService.findContaById(dto.getRemetente()).getBody();

        if (remetente.getTipo().equals(TipoConta.CONTA_CORRENTE)){
            if (remetente.getSaldo().add(limite).doubleValue() > dto.getValor().doubleValue()){
                
                remetente.setSaldo(remetente.getSaldo().subtract(dto.getValor()));
                destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));
                contaService.realizarOperacao(remetente);
                contaService.realizarOperacao(destinatario);
            }
            else {
                return ResponseEntity.badRequest().body("Valor da transação não pode exceder {saldo: " + remetente.getSaldo() + " + limite: " + limite + "}");
            }
        }else if (remetente.getTipo().equals(TipoConta.CONTA_POUPANCA)) {
            if (remetente.getSaldo().doubleValue() > dto.getValor().doubleValue()) {

                remetente.setSaldo(remetente.getSaldo().subtract(dto.getValor()));
                destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));
                contaService.realizarOperacao(remetente);
                contaService.realizarOperacao(destinatario);

            } else {
                return ResponseEntity.badRequest().body("Valor da transação não pode exceder {saldo: " + remetente.getSaldo() + "}");
            }
        }

        transacao.setData(Timestamp.valueOf(LocalDateTime.now()));
        transacao.setDestinatario(destinatario);
        transacao.setRemetente(remetente);
        transacao = transacaoRepository.save(transacao);

        URI uri = new URI("/transacao/" + transacao.getId());

        return ResponseEntity.created(uri).body(transacao);
    };

    public ResponseEntity aplicarRendimentoMensal(TransacaoDTO dto, Double taxa) throws URISyntaxException {

        Transacao transacao = new Transacao(dto);

        Conta destinatario = contaService.findContaById(dto.getDestinatario()).getBody();

        if (destinatario.getTipo().equals(TipoConta.CONTA_POUPANCA) && transacao.getTipo().equals(TipoTransacao.RENDIMENTO)){

            destinatario.setSaldo(destinatario.getSaldo().add(destinatario.getSaldo().multiply(BigDecimal.valueOf(taxa)).divide(BigDecimal.valueOf(100))));

            contaService.realizarOperacao(destinatario);

        }else {
            return ResponseEntity.badRequest().body("Tipo de conta e(ou) transação errados");
        };

        transacao.setData(Timestamp.valueOf(LocalDateTime.now()));
        transacao.setDestinatario(destinatario);
        transacaoRepository.save(transacao);

        URI uri = new URI("/transacao/" + transacao.getId());

        return ResponseEntity.created(uri).body(transacao);
    }

    public ResponseEntity<List<Transacao>> findTransacaoByDestinatario(Long id){

        Conta destinatario = contaService.findContaById(id).getBody();
        return ResponseEntity.ok(transacaoRepository.findAllByDestinatario(destinatario));

    };

    public ResponseEntity<Transacao> findTransacaoById(Long id){
        return ResponseEntity.ok(transacaoRepository.findById(id).get());
    };

}
