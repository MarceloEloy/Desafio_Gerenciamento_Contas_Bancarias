package com.example.DesafioGerenciamentoContasBancarias.model.DTOS;

import com.example.DesafioGerenciamentoContasBancarias.model.Transacao;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {
    public TransacaoDTO(Transacao transacao){
        this.id = transacao.getId();
        this.data = transacao.getData();
        this.valor = transacao.getValor();

        if (transacao.getDestinatario() != null) {
            this.destinatario = transacao.getDestinatario().getId();
        }
        if (transacao.getRemetente() != null) {
            this.remetente = transacao.getRemetente().getId();
        }
    }

    private Long id;

    private TipoTransacao tipo;

    private BigDecimal valor;

    private Timestamp data;

    private Long remetente;

    private Long destinatario;

}
