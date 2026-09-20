package com.example.DesafioGerenciamentoContasBancarias.model.DTOS;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrentistaDTO {
    public CorrentistaDTO(Correntista correntista){

        this.id = correntista.getId();
        this.nome = correntista.getNome();
        this.documento = correntista.getDocumento();
        this.contato = correntista.getContato();
        this.contas = correntista.getContas();


    }

    private Long id;

    private String nome;

    private String documento;

    private String contato;

    private List<Conta> contas;


}

