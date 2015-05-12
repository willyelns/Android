package br.ufrn.imd.itamir.dominio;

import java.io.Serializable;

/**
 * Created by itamir on 05/05/2015.
 */
public class Fornecedor implements Serializable {

    private String nome;

    private String cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
