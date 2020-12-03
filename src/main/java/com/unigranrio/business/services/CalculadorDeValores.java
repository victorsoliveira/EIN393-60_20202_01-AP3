package com.unigranrio.business.services;

import com.unigranrio.business.strategies.CalculaAluguelDiaNaoUtil;
import com.unigranrio.business.strategies.CalculaAluguelDiaUil;
import com.unigranrio.business.utils.DateUtil;
import java.math.BigDecimal;
import java.util.Date;

public class CalculadorDeValores {

    private CalculaAluguelDiaUil _caculaAluguelDiaUtil;
    private CalculaAluguelDiaNaoUtil _caculaAluguelDiaNaoUtil;

    public CalculadorDeValores() {
        _caculaAluguelDiaUtil = new CalculaAluguelDiaUil();
        _caculaAluguelDiaNaoUtil = new CalculaAluguelDiaNaoUtil();
    }

    public BigDecimal calcula(Date date, BigDecimal precoBase, int dias) {

        if (DateUtil.isWeekendDay(date)){
            return _caculaAluguelDiaNaoUtil.aplicaCalculo(date, precoBase, dias);
        }

        return _caculaAluguelDiaUtil.aplicaCalculo(date, precoBase, dias);
    }
}
