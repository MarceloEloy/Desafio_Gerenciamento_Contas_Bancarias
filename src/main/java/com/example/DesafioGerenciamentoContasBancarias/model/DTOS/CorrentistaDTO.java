package com.example.DesafioGerenciamentoContasBancarias.model.DTOS;

import com.example.DesafioGerenciamentoContasBancarias.model.Conta;
import com.example.DesafioGerenciamentoContasBancarias.model.Correntista;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrentistaDTO {
    public CorrentistaDTO(Correntista correntista){

        this.nome = correntista.getNome();
        this.documento = correntista.getDocumento();
        this.contato = correntista.getContato();
        this.contas = correntista.getContas();


    }

    @NotNull(message = "campo {nome} não deve ser nulo")
    @NotEmpty(message = "campo {nome} não dever ser vazio")
    private String nome;

    @NotNull(message = "campo {documento} não deve ser nulo")
    @NotBlank(message = "campo {documento} não deve ser vazio")
    @Pattern(regexp = "^\\d{11}$", message = "campo {documento} deve conter 11 digitos")
    private String documento;

    @NotNull(message = "campo {contato} não deve ser nulo")
    @NotEmpty(message = "campo {contato} não dever ser vazio")
    @Pattern(regexp = "^\\d{10,11}$", message = "campo {contato} deve conter de 10 a 11 digitos")
    private String contato;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<Conta> contas;


}

