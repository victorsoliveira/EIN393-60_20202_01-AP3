package com.unigranrio.business.strategies;

import java.math.BigDecimal;

public class CalculaAluguelDiaUil extends CalculaAluguel{

    @Override
    BigDecimal calcularValorAluguel(BigDecimal precoBase) {
        return precoBase;
    }
}
