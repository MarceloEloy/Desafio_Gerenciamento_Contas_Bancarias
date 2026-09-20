package com.example.DesafioGerenciamentoContasBancarias.repositorys;

import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
