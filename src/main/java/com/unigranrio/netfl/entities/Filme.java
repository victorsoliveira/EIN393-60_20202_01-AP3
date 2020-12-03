package com.unigranrio.netfl.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;


    private String descricao;
    private BigDecimal valor;

    public Filme() {}

    public Filme(String titulo, String descricao, BigDecimal valor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
