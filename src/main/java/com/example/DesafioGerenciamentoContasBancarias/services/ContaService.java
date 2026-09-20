package com.example.DesafioGerenciamentoContasBancarias.services;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.ContaDTO;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.ContaRepository;
import com.example.DesafioGerenciamentoContasBancarias.repositorys.CorrentistaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    private final CorrentistaService correntistaService;

    public ResponseEntity<Conta> adicionarConta(ContaDTO dto) throws URISyntaxException {

        Conta conta = new Conta(dto);

        conta.setCorrentista(correntistaService.findCorrentistaById(dto.getCorrentista()).getBody());

        conta = contaRepository.save(conta);

        URI uri = new URI("/conta/" + conta.getId());

        return ResponseEntity.created(uri).body(conta);

    };

    @Transactional
    public void realizarOperacao(Conta contaAlterada){
        Conta conta = contaRepository.findById(contaAlterada.getId()).get();

        if (conta.getSaldo() != contaAlterada.getSaldo()){
            conta.setSaldo(contaAlterada.getSaldo());
        };

    }

    public ResponseEntity<Conta> findContaById(Long id){
        return ResponseEntity.ok(contaRepository.findById(id).get());
    }

    public ResponseEntity<List<Conta>> findAllContasByCorrentistaId(Long id){

        Correntista correntista = correntistaService.findCorrentistaById(id).getBody();

        return ResponseEntity.ok(contaRepository.findAllByCorrentista(correntista));
    }

}
