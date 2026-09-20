package com.example.DesafioGerenciamentoContasBancarias.model;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.ContaDTO;
import com.example.DesafioGerenciamentoContasBancarias.model.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    public Conta(ContaDTO dto){

        if (dto.getId() != null){
            this.id = dto.getId();
        };
        if (dto.getTransacoesDestinatario() != null){
            this.transacoesDestinatario = dto.getTransacoesDestinatario();
        }
        if (dto.getTransacoesRemetente() != null){
            this.transacoesRemetente = dto.getTransacoesRemetente();
        }
        this.numero = dto.getNumero();
        this.saldo = dto.getSaldo();
        this.tipo = dto.getTipo();

    }

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
    @JsonBackReference
    private Correntista correntista;

    @OneToMany(mappedBy = "remetente")
    private List<Transacao> transacoesRemetente;

    @OneToMany(mappedBy = "destinatario")
    private List<Transacao> transacoesDestinatario;


    @Override
    public String toString() {
        return super.toString();
    }
}
