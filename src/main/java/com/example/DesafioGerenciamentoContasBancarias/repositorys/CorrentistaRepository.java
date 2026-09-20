package com.example.DesafioGerenciamentoContasBancarias.repositorys;

import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}
