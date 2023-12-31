/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.bd2.models;

import com.mycompany.api.bd2.daos.clienteDAO;

/**
 *
 * @author csous
 */
public class TabelaAprovaçãoGestor {
    
    private Integer id;
    private String username_lancador;
    private Integer cod_cr;
    private String nome_cr;
    private String empresa;
    private String projeto;
    private String tipo;
    private String inicio;
    private String fim;
    private String justificativa;
    private String justificativa_lancamento;

    public String getUsername() {
        return username_lancador;
    }
    
     public String getUsername_lancador() {
        return username_lancador;
    }


    public void setUsername(String username) {
        this.username_lancador = username;
    }

    public int getCod_cr() {
        return cod_cr;
    }

    public void setCod_cr(String cod_cr) {
        this.cod_cr = Integer.parseInt(cod_cr);
    }

    public String getNome_cr() {
        return nome_cr;
    }

    public void setNome_cr(String nome_cr) {
        this.nome_cr = nome_cr;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int cnpj) {
        clienteDAO clientedao = new clienteDAO();
        this.empresa = clientedao.getClientebyCNPJ(cnpj).getRazao_social();
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getJustificativa_lancamento() {
        return justificativa_lancamento;
    }

    public void setJustificativa_lancamento(String justificativa_lancamento) {
        this.justificativa_lancamento = justificativa_lancamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
