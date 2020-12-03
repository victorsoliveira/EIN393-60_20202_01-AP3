package com.unigranrio.netfl.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "alugueis")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Filme filme;

    private BigDecimal valor;
    private int dias;
    private Date data;

    public Aluguel() {}
    public Aluguel(Date data, Cliente cliente, Filme filme, BigDecimal valor, int dias) {
        this.data = data;
        this.cliente = cliente;
        this.filme = filme;
        this.valor = valor;
        this.dias = dias;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }

    public Date getData() {
        return data;
    }
}
