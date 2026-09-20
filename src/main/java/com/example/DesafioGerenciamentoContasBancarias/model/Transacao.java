package com.example.DesafioGerenciamentoContasBancarias.model;

import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoConta tipo;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data")
    private Timestamp data;

    @ManyToOne()
    @JoinColumn(name = "id_conta_remetente")
    private Conta remetente;

    @ManyToOne()
    @JoinColumn(name = "id_conta_destinatario")
    private Conta destinatario;

}
