package com.example.DesafioGerenciamentoContasBancarias.model;

import com.example.DesafioGerenciamentoContasBancarias.model.DTOS.CorrentistaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "correntista")
@AllArgsConstructor
@NoArgsConstructor
public class Correntista {
    public Correntista(CorrentistaDTO dto){
        if (dto.getContas() != null){
            this.contas = dto.getContas();
        }
        this.nome = dto.getNome();
        this.documento = dto.getDocumento();
        this.contato = dto.getContato();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "documento")
    private String documento;

    @Column(name = "contato")
    private String contato;

    @OneToMany(mappedBy = "correntista")
    private List<Conta> contas;


}


