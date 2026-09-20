package com.example.DesafioGerenciamentoContasBancarias.model.DTOS;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "campo {nome} não deve ser nulo")
    @NotEmpty(message = "campo {nome} não dever ser vazio")
    private String nome;

    @NotNull(message = "campo {documento} não deve ser nulo")
    private String documento;

    @NotNull(message = "campo {contato} não deve ser nulo")
    private String contato;

    private List<Conta> contas;


}

