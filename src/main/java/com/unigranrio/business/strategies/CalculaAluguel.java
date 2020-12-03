package com.unigranrio.business.strategies;

import java.math.BigDecimal;
import java.util.Date;

public abstract class CalculaAluguel {
    abstract BigDecimal calcularValorAluguel(BigDecimal precoBase);

    public BigDecimal aplicaCalculo(Date data, BigDecimal precoBase, int dias) {

        BigDecimal result = BigDecimal.ZERO;

        for (int i = 1; i <= dias; i++) {
            result.add(calcularValorAluguel(precoBase));
        }

        return result;

    }
}
