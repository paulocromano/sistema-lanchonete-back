package br.com.sistemalanchonete.endereco.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnderecoAPIViaCEP {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Boolean erro;
}
