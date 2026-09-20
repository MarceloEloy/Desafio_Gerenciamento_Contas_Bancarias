package com.example.DesafioGerenciamentoContasBancarias.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "documento")
    String documento;

    @Column(name = "contato")
    String contato;

    @OneToMany(mappedBy = "correntista")
    private List<Conta> contas;


}


