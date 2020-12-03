package com.unigranrio.netfl.dtos;

import com.unigranrio.netfl.entities.Filme;

import java.math.BigDecimal;

public class FilmeDTO {

    public String titulo;
    public String descricao;
    public BigDecimal valor;

    public FilmeDTO(Filme filme) {
        this.titulo = filme.getTitulo();
        this.descricao = filme.getDescricao();
        this.valor = filme.getValor();
    }
}
