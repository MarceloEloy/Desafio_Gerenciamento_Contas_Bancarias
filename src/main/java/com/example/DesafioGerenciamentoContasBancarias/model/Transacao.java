package com.example.DesafioGerenciamentoContasBancarias.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "transacao")
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
}
