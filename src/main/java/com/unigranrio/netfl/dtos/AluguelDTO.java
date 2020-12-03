package com.unigranrio.netfl.dtos;

import com.unigranrio.business.utils.DateUtil;
import com.unigranrio.netfl.entities.Aluguel;

import java.math.BigDecimal;
import java.util.Date;

public class AluguelDTO {

    public String nomeCliente;
    public String tituloFilme;
    public Date dataAluguel;
    public Date dataDevolucao;
    public BigDecimal subTotal;
    public BigDecimal total;

    public AluguelDTO(Aluguel entity) {

        this.nomeCliente = entity.getCliente().getNome();
        this.tituloFilme = entity.getFilme().getTitulo();
        this.dataAluguel = entity.getData();
        this.dataDevolucao = DateUtil.incrementDate(entity.getData(), entity.getDias());
        this.subTotal = entity.getValor();
        this.total = (entity.getValor().multiply(new BigDecimal(entity.getDias())));
    }

}
