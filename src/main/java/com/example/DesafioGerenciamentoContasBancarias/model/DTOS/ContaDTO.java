package com.example.DesafioGerenciamentoContasBancarias.model.DTOS;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {
    public ContaDTO(Conta conta){

        this.id = conta.getId();
        this.numero = conta.getNumero();
        this.saldo = conta.getSaldo();
        this.tipo = conta.getTipo();
        if (conta.getTransacoesDestinatario() != null) {
            this.transacoesDestinatario = conta.getTransacoesDestinatario();
        }
        if (conta.getTransacoesRemetente() != null) {
            this.transacoesRemetente = conta.getTransacoesRemetente();
        }

    }

    private Long id;

    @NotNull(message = "Campo {numero} não deve ser nulo")
    @NotEmpty(message = "Campo {numero} não deve ser vazio")
    private String numero;

    private BigDecimal saldo;

    @NotNull(message = "Campo {tipo} não deve ser nulo")
    private TipoConta tipo;

    @NotNull(message = "Campo {correntista} não deve ser nulo")
    private Long correntista;

    private List<Transacao> transacoesRemetente;

    private List<Transacao> transacoesDestinatario;

}
