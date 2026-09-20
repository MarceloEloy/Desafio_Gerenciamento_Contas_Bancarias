package com.example.DesafioGerenciamentoContasBancarias.services;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.TransacaoDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.ContaRepository;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Transacao> adicionarTransacao(TransacaoDTO dto) throws URISyntaxException {

        Transacao transacao = new Transacao(dto);
        transacao.setDestinatario(contaRepository.findById(dto.getDestinatario()).get());
        transacao.setRemetente(contaRepository.findById(dto.getRemetente()).get());

        transacao.setData(Timestamp.valueOf(LocalDateTime.now()));

        transacao = transacaoRepository.save(transacao);

        URI uri = new URI("/transacao/" + transacao.getId());

        return ResponseEntity.created(uri).body(transacao);

    }

    public ResponseEntity<Transacao> findTransacaoById(Long id){
        return ResponseEntity.ok(transacaoRepository.findById(id).get());
    }

}
