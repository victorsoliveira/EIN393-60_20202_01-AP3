package com.unigranrio.business.strategies;

import java.math.BigDecimal;

public class CalculaAluguelDiaNaoUtil extends CalculaAluguel {

    @Override
    BigDecimal calcularValorAluguel(BigDecimal precoBase) {
        return precoBase.multiply(new BigDecimal(1.5));
    }

}
