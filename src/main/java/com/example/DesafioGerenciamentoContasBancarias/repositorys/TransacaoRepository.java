package com.example.DesafioGerenciamentoContasBancarias.repositorys;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByDestinatario(Conta conta);

    List<Transacao> findAllByRemetente(Conta conta);

}
