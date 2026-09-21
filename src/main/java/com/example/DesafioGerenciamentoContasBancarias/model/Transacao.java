package com.example.DesafioGerenciamentoContasBancarias.model;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.TransacaoDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "transacao")
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    public Transacao(TransacaoDTO dto){
        this.data = dto.getData();
        this.valor = dto.getValor();
        this.tipo = dto.getTipo();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTransacao tipo;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data")
    private Timestamp data;

    @ManyToOne()
    @JoinColumn(name = "id_conta_remetente")
    @JsonBackReference("remetente")
    private Conta remetente;

    @ManyToOne()
    @JoinColumn(name = "id_conta_destinatario")
    @JsonBackReference("destinatario")
    private Conta destinatario;

}
