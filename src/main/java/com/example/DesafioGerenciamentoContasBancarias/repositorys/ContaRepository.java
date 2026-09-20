package com.example.DesafioGerenciamentoContasBancarias.repositorys;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
