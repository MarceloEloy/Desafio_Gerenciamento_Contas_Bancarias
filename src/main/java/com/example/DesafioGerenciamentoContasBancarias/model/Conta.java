package com.example.DesafioGerenciamentoContasBancarias.model;

import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoConta tipo;

    @ManyToOne()
    @JoinColumn(name = "id_correntista")
    private Correntista correntista;

    @OneToMany(mappedBy = "remetente")
    private List<Transacao> transacoesRemetente;

    @OneToMany(mappedBy = "destinatario")
    private List<Transacao> transacoesDestinatario;

}
